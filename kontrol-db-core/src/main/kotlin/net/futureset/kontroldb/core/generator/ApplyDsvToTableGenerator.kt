package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorFactory
import net.futureset.kontroldb.model.ColumnAndValue
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.model.StandardColumnTypes.DATE
import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT16
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.INT64
import net.futureset.kontroldb.model.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.modelchange.ApplyDsvToTable
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.modelchange.InsertOrUpdateRow
import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.modelchange.ValuesBuilder
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.settings.SQL_DATE_FORMAT
import net.futureset.kontroldb.settings.SQL_TIMESTAMP_FORMAT
import org.koin.core.annotation.Singleton
import java.time.LocalDate
import java.time.LocalDateTime

@Singleton(binds = [SqlGenerator::class])
class ApplyDsvToTableGenerator(es: EffectiveSettings, private val sqlGeneratorFactory: SqlGeneratorFactory) : DbAwareGenerator<ApplyDsvToTable>(es, ApplyDsvToTable::class) {
    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convert(change: ApplyDsvToTable): List<String> {
        require(!change.useDbLoadingTool)
        return change.file.reader().use { reader ->
            val headerNames = reader.readLine()
                .split(change.separator)
                .map(String::uppercase)
            val invalidHeaderNames = change.headerMappings.keys.filterNot { headerNames.contains(it) }
            require(invalidHeaderNames.isEmpty()) { "No such headers '$invalidHeaderNames' CSV has $headerNames" }
            val values = reader.lineSequence()
                .mapIndexed { _, row ->
                    val v = ValuesBuilder()
                    row.split(change.separator).mapIndexed { columNumber, value ->
                        requireNotNull(change.headerMappings[headerNames[columNumber]]).let {
                            when (it.columnType) {
                                is Decimal -> v.value(
                                    it.columnName.name,
                                    value.toBigDecimal().setScale(it.columnType.scale),
                                )

                                BOOLEAN -> {
                                    v.value(
                                        it.columnName.name,
                                        value.lowercase() in trueBooleanStrings,
                                    )
                                }
                                DATE -> v.value(it.columnName.name, LocalDate.parse(value, SQL_DATE_FORMAT))
                                LOCALDATETIME -> v.value(it.columnName.name, LocalDateTime.parse(value, SQL_TIMESTAMP_FORMAT))
                                INT64, INT32, INT16 -> v.value(
                                    it.columnName.name,
                                    value.toLong(),
                                )

                                else -> v.value(it.columnName.name, value)
                            }
                        }
                    }
                    v.build()
                }.toList()
            val changes = mutableListOf<ModelChange>()
            if (change.updateRows || change.ignoreInsertViolations) {
                changes.add(
                    InsertOrUpdateRow.InsertOrUpdateRowBuilder(change.table.schemaObject.name.name)
                        .primaryKey(*change.primaryKeys.map(DbIdentifier::name).toTypedArray())
                        .table(change.table).addRows(values)
                        .mode(
                            if (change.updateRows && change.insertRows) {
                                UpdateMode.UPDATE_AND_INSERT
                            } else if (change.updateRows) {
                                UpdateMode.UPDATE
                            } else {
                                UpdateMode.INSERT
                            },
                        ).build(),
                )
            } else {
                changes.add(InsertRows.InsertRowsBuilder().table(change.table).addRows(values).build())
            }
            if (change.deleteRows) {
                val tempTable = Table(SchemaObject(name = DbIdentifier("PK_VALUES")), TablePersistence.TEMPORARY)
                changes.add(
                    CreateTable(
                        table = tempTable,
                        columnDefinitions = emptyList(),
                        tablespace = null,
                        preserveRowsOnCommit = true,
                        primaryKey = AddPrimaryKey(table = tempTable, columnReferences = change.primaryKeys.toList(), clustered = true, inline = true),
                        fromSelect = SelectQuery(
                            columns = change.primaryKeys.map { ColumnAndValue(it, null) },
                            table = change.table.alias(),
                            includeData = false,
                        ),
                    ),
                )
                changes.add(
                    InsertRows.InsertRowsBuilder().table(tempTable)
                        .addRows(values.map { it.filter { it.key in change.primaryKeys } }).build(),
                )
                changes.add(
                    DeleteRows.DeleteRowsBuilder()
                        .tableWithAlias(change.table, "A")
                        .where {
                            not {
                                existsSelect {
                                    column("COL", "1")
                                    table(tempTable).alias("P")
                                    where {
                                        change.primaryKeys.forEach { it.alias("P") eq it.alias("A") }
                                    }
                                }
                            }
                        }.build(),
                )
            }
            changes.flatMap { sqlGeneratorFactory.generateSql(it) }
        }
    }

    companion object {
        private val trueBooleanStrings = setOf("true", "yes", "on", "y", "1")
    }
}

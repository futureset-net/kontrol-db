package net.futureset.kontroldb.template

import net.futureset.kontroldb.ColumnAndValue
import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.StandardColumnTypes.DATE
import net.futureset.kontroldb.StandardColumnTypes.Decimal
import net.futureset.kontroldb.StandardColumnTypes.INT_16
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.StandardColumnTypes.INT_64
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.modelchange.ApplyDsvToTable
import net.futureset.kontroldb.modelchange.CreateTemporaryTable
import net.futureset.kontroldb.modelchange.DeleteRow
import net.futureset.kontroldb.modelchange.Insert
import net.futureset.kontroldb.modelchange.InsertOrUpdateRow
import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.modelchange.ValuesBuilder
import net.futureset.kontroldb.settings.EffectiveSettings
import java.time.LocalDate
import kotlin.reflect.KClass

class ApplyDsvToTableTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<ApplyDsvToTable>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<ApplyDsvToTable> {
        return ApplyDsvToTable::class
    }

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
                                DATE -> v.value(it.columnName.name, LocalDate.parse(value))
                                INT_64, INT_32, INT_16 -> v.value(
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
                    InsertOrUpdateRow.InsertOrUpdateRowBuilder().primaryKey(*change.primaryKeys.map(DbIdentifier::name).toTypedArray())
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
                changes.add(Insert.InsertBuilder().table(change.table).addRows(values).build())
            }
            if (change.deleteRows) {
                val tempTable = SchemaObject(name = DbIdentifier("PK_VALUES"))
                changes.add(
                    CreateTemporaryTable(
                        table = tempTable,
                        columnDefinitions = emptyList(),
                        tablePersistence = TablePersistence.TEMPORARY,
                        preserveRowsOnCommit = true,
                        primaryKey = AddPrimaryKey(table = null, columnReferences = change.primaryKeys.toList(), clustered = true),
                        fromSelect = SelectQuery(
                            columns = change.primaryKeys.map { ColumnAndValue(it, null) },
                            table = change.table.alias(),
                            includeData = false,
                        ),
                    ),
                )
                changes.add(
                    Insert.InsertBuilder().table(tempTable)
                        .addRows(values.map { it.filter { it.key in change.primaryKeys } }).build(),
                )
                changes.add(
                    DeleteRow.DeleteRowBuilder()
                        .tableWithAlias(change.table, "A")
                        .where {
                            not {
                                existsSelect {
                                    column("COL", "1")
                                    table(tempTable)
                                    where {
                                        change.primaryKeys.forEach { it eq it.alias("A") }
                                    }
                                }
                            }
                        }.build(),
                )
            }
            changes.flatMap(::otherTemplateOutput)
        }
    }

    companion object {
        private val trueBooleanStrings = setOf("true", "yes", "on", "y", "1")
    }
}

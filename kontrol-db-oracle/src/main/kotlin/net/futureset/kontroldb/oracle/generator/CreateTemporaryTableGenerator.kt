package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorFactory
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class CreateTemporaryTableGenerator(es: EffectiveSettings, private val sqlGeneratorFactory: SqlGeneratorFactory) : DbAwareGenerator<CreateTable>(es, CreateTable::class) {

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "oracle"

    override fun convertSingle(): CreateTable.() -> String? = {
        val colNames =
            fromSelect?.columns?.map { it.columnName } ?: columnDefinitions.takeUnless { it.isEmpty() }.orEmpty()
        """
            ${
            when (table.tablePersistence) {
                TablePersistence.TEMPORARY -> "CREATE GLOBAL TEMPORARY "
                TablePersistence.GLOBAL_TEMPORARY -> "CREATE GLOBAL TEMPORARY "
                TablePersistence.NORMAL -> "CREATE "
            }
        }TABLE ${table.toQuoted()} (
            ${
            joinQuotableValues(
                colNames,
                separateBy = ",\n    ",
            )
        }${
            primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }?.let {
                "," + sqlGeneratorFactory.generateSqlSingle(it)
            }.orEmpty()
        } )
                       ${
            "ON COMMIT ${if (preserveRowsOnCommit) "PRESERVE" else "DELETE"} ROWS".takeIf { table.tablePersistence != TablePersistence.NORMAL }
                .orEmpty()
        }
        ${
            fromSelect?.let { selectQuery ->
                " AS (" +
                    sqlGeneratorFactory.generateSql(selectQuery).first() +
                    (if (selectQuery.predicate?.isEmpty() != false) " WHERE 1=0" else " AND 1=0")
                        .takeUnless { selectQuery.includeData }.orEmpty() + ")"
            }.orEmpty()
        }
        """.trimIndent()
    }
}

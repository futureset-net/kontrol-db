package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorFactory
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class CreateTemporaryTableGenerator(
    es: EffectiveSettings,
    private val sqlGeneratorFactory: SqlGeneratorFactory,
) : DbAwareGenerator<CreateTable>(es, CreateTable::class) {
    override fun canApplyTo(es: EffectiveSettings): Boolean = this.es.databaseName == "hsqldb"

    override fun convertSingle(): CreateTable.() -> String? = {
        val colNames =
            columnDefinitions.takeUnless { it.isEmpty() }
                ?: fromSelect?.columns?.map { it.columnName }.orEmpty()
        when (table.tablePersistence) {
            TablePersistence.TEMPORARY -> "DECLARE LOCAL TEMPORARY "
            TablePersistence.GLOBAL_TEMPORARY -> "CREATE GLOBAL TEMPORARY "
            TablePersistence.NORMAL -> "CREATE "
        } + (
            """TABLE ${table.toQuoted()} (
             ${joinQuotableValues(colNames, separateBy = ",\n    ")}
                ${
                primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }?.let {
                    "," +
                        sqlGeneratorFactory.generateSqlSingle(
                            it,
                        )
                }
                    .orEmpty()
            } 
                )${
                fromSelect?.let {
                    " AS (${sqlGeneratorFactory.generateSqlSingle(it)}) WITH ${if (it.includeData) "" else "NO "}DATA"
                }.orEmpty()
            }
                ${
                "ON COMMIT PRESERVE ROWS".takeIf { preserveRowsOnCommit && table.tablePersistence != TablePersistence.NORMAL }
                    .orEmpty()
            }
            
            """.trimIndent()
            )
    }
}

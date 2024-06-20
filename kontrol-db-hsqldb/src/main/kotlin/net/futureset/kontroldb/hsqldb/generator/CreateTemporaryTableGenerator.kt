package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class CreateTemporaryTableGenerator(private val db: EffectiveSettings) :
    DbAwareGenerator<CreateTable>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "hsqldb"
    override fun convertSingle(): CreateTable.() -> String? = {
        val colNames = columnDefinitions.takeUnless { it.isEmpty() }
            ?: fromSelect?.columns?.map { it.columnName }.orEmpty()
        when (table.tablePersistence) {
            TablePersistence.TEMPORARY -> "DECLARE LOCAL TEMPORARY "
            TablePersistence.GLOBAL_TEMPORARY -> "CREATE GLOBAL TEMPORARY "
            TablePersistence.NORMAL -> "CREATE "
        } + """ TABLE ${table.toQuoted()} (
            ${joinQuotableValues(colNames, separateBy = ",\n    ")}
            ${
            primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }?.let { "," + generateSqlSingle(it) }
                .orEmpty()
        } 
            )${
            fromSelect?.let {
                " AS (${generateSqlSingle(it)}) WITH ${if (it.includeData) "" else "NO "}DATA"
            }.orEmpty()
        }
            ${
            "ON COMMIT PRESERVE ROWS".takeIf { preserveRowsOnCommit && table.tablePersistence != TablePersistence.NORMAL }
                .orEmpty()
        }
        """.trimIndent()
    }
}

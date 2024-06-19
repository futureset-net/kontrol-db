package net.futureset.kontroldb.hsqldb.template

import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateTemporaryTableTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<CreateTable>(db, TemplatePriority.DATABASE) {
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
        } + """ TABLE ${table.toSql()} (
            ${forEach(colNames, separateBy = ",\n    ")}
            ${
            primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }?.let { "," + otherTemplate(it) }
                .orEmpty()
        } 
            )${
            fromSelect?.let {
                " AS (${
                    template(it)?.convert(it)?.first()
                }) WITH ${if (it.includeData) "" else "NO "}DATA"
            }.orEmpty()
        }
            ${
            "ON COMMIT PRESERVE ROWS".takeIf { preserveRowsOnCommit && table.tablePersistence != TablePersistence.NORMAL }
                .orEmpty()
        }
        """.trimIndent()
    }
}

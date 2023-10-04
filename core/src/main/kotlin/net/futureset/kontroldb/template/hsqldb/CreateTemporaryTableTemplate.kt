package net.futureset.kontroldb.template.hsqldb

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.CreateTemporaryTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class CreateTemporaryTableTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<CreateTemporaryTable>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<CreateTemporaryTable> {
        return CreateTemporaryTable::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "hsqldb"

    override fun convertToSingleStatement(change: CreateTemporaryTable): String {
        val colNames = change.columnDefinitions.takeUnless { it.isEmpty() } ?: change.fromSelect?.columns?.map { it.columnName }.orEmpty()
        return """
            ${if (change.tablePersistence == TablePersistence.TEMPORARY) "DECLARE LOCAL " else "CREATE GLOBAL "}TEMPORARY TABLE ${change.table.toSql()} (
            ${forEach(colNames, separateBy = ",\n    ")}  
            )${change.fromSelect?.let{ " AS (${template(it)?.convert(it)?.first()}) WITH ${if (it.includeData) "" else "NO "}DATA"}.orEmpty()}
            ${"ON COMMIT PRESERVE ROWS".takeIf { change.preserveRowsOnCommit }.orEmpty()}
        """.trimIndent()
    }
}

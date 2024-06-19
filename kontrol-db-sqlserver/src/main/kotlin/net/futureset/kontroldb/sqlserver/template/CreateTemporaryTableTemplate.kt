package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import net.futureset.kontroldb.template.trimBlankLines
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateTemporaryTableTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<CreateTable>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "sqlserver"

    override fun convertSingle(): CreateTable.() -> String = {
        val colNames = columnDefinitions.takeUnless { it.isEmpty() }
            ?: fromSelect?.columns?.map { it.columnName }.orEmpty()
        val selectQuery = fromSelect
        if (selectQuery != null) {
            otherTemplate(selectQuery).replaceFirst("FROM", "INTO ${table.toSql()}\nFROM") +
                if (selectQuery.includeData) {
                    ""
                } else if (selectQuery.predicate?.isEmpty() != false) {
                    "WHERE 1=0"
                } else {
                    "AND 1=0"
                }
        } else {
            """
            CREATE TABLE ${table.toSql()} (
                ${forEach(colNames, separateBy = ",\n                ")}
            ${
                primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }
                    ?.let { "," + otherTemplate(it) }.orEmpty()
            } 
            )
            """.trimIndent().trimBlankLines()
        }
    }
}

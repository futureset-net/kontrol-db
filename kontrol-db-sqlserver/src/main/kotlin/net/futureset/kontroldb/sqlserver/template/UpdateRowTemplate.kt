package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import net.futureset.kontroldb.template.trimBlankLines
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class UpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<UpdateRows>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<UpdateRows> {
        return UpdateRows::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean =
        effectiveSettings.databaseName == "sqlserver"

    override fun convertSingle(): UpdateRows.() -> String? = {
        """
            UPDATE ${table.alias ?: table.table.toSql()}
            SET ${forEach(columnValues)}         
            ${if (table.alias != null) table.toSql { " FROM $it" } else ""}
            ${predicate.takeUnless { it.isEmpty() }.toSql { "WHERE $it" }}
        """.trimIndent().trimBlankLines()
    }
}

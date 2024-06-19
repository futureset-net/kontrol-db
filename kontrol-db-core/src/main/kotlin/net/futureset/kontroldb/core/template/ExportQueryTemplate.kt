package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.ExportQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ExportQueryTemplate(db: EffectiveSettings) :
    DbAwareTemplate<ExportQuery>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<ExportQuery> {
        return ExportQuery::class
    }

    override fun convertSingle(): ExportQuery.() -> String? = {
        """
SELECT
${forEach(selectQuery.columns, separateBy = ",\n    ")}
${selectQuery.table.toSql { "FROM $it" }}
${selectQuery.predicate.toSql { "WHERE $it" }}
        """.trimIndent()
    }
}

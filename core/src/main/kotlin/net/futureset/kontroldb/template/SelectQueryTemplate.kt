package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class SelectQueryTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<SelectQuery>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<SelectQuery> {
        return SelectQuery::class
    }

    override fun convertToSingleStatement(change: SelectQuery): String {
        return """
SELECT
    ${forEach(change.columns, separateBy = ",\n    ")}
    ${change.table.toSql {"FROM $it"}}
    ${change.predicate.toSql {"WHERE $it"}}
        """.trimIndent()
    }
}

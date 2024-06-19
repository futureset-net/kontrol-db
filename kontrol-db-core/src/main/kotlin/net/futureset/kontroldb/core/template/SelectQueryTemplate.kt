package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class SelectQueryTemplate(db: EffectiveSettings) :
    DbAwareTemplate<SelectQuery>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<SelectQuery> {
        return SelectQuery::class
    }

    override fun convertSingle(): SelectQuery.() -> String? = {
        "SELECT " + forEach(columns, separateBy = ",\n        ") +
            table.toSql { " FROM $it" } +
            predicate.toSql { " WHERE $it" }
    }
}

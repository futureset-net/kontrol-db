package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.ExportQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ExportQueryTemplate(db: EffectiveSettings) :
    DbAwareTemplate<ExportQuery>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<ExportQuery> {
        return ExportQuery::class
    }

    override fun convertToSingleStatement(change: ExportQuery): String {
        return """
SELECT
    ${forEach(change.selectQuery.columns, separateBy = ",\n    ")}
    ${change.selectQuery.table.toSql {"FROM $it"}}
    ${change.selectQuery.predicate.toSql {"WHERE $it"}}
        """.trimIndent()
    }
}

package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DeleteRow
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class DeleteRowTemplate(private val db: EffectiveSettings) : DbAwareTemplate<DeleteRow>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DeleteRow> {
        return DeleteRow::class
    }

    override fun convertToSingleStatement(change: DeleteRow): String {
        return """
DELETE FROM ${change.table.toSql()}        
${change.predicate.toSql {"WHERE $it"}}         
        """.trimIndent()
    }
}

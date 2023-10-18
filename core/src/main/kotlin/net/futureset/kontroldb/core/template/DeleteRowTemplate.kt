package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DeleteRow
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DeleteRowTemplate(db: EffectiveSettings) : DbAwareTemplate<DeleteRow>(db, TemplatePriority.DEFAULT) {
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

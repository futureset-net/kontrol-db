package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DeleteRowsTemplate(db: EffectiveSettings) : DbAwareTemplate<DeleteRows>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DeleteRows> {
        return DeleteRows::class
    }

    override fun convertToSingleStatement(change: DeleteRows): String {
        return """
        DELETE FROM ${change.table.toSql()}        
        ${change.predicate.toSql {"WHERE $it"}}
        """.trimIndent()
    }
}

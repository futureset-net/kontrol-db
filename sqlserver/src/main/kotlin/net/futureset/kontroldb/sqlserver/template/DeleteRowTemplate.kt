package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DeleteRowTemplate(db: EffectiveSettings) : DbAwareTemplate<DeleteRows>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DeleteRows> {
        return DeleteRows::class
    }

    override fun convertToSingleStatement(change: DeleteRows): String {
        return """
DELETE ${change.table.alias ?: "X"}   
FROM ${change.table.copy(alias = change.table.alias ?: "X").toSql()}        
${change.predicate.toSql {"WHERE $it"}}         
        """.trimIndent()
    }
}

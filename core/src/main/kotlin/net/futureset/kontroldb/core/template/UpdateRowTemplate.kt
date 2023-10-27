package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class UpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<UpdateRows>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<UpdateRows> {
        return UpdateRows::class
    }

    override fun convertToSingleStatement(change: UpdateRows): String {
        return """
            UPDATE ${change.table.toSql()}
            SET ${forEach(change.columnValues)}         
            ${change.predicate.takeUnless { it.isEmpty() }.toSql {"WHERE $it"} }
        """.trimIndent()
    }
}

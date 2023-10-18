package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Update
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class UpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<Update>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<Update> {
        return Update::class
    }

    override fun convertToSingleStatement(change: Update): String {
        return """
            UPDATE ${change.table.toSql()}
            SET ${forEach(change.columnValues)}         
            ${change.predicate.takeUnless { it.isEmpty() }.toSql {"WHERE $it"} }
        """.trimIndent()
    }
}

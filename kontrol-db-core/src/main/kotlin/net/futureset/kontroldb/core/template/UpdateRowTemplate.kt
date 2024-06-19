package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class UpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<UpdateRows>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<UpdateRows> {
        return UpdateRows::class
    }

    override fun convertSingle(): UpdateRows.() -> String? = {
        """
        UPDATE ${table.toSql()}
        SET ${forEach(columnValues)}         
        ${predicate.takeUnless { it.isEmpty() }.toSql {"WHERE $it"} }
        """.trimIndent()
    }
}

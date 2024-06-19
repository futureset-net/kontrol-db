package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class AddNotNullTemplate(db: EffectiveSettings) : DbAwareTemplate<AddNotNull>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }

    override fun convertSingle(): AddNotNull.() -> String? = {
        table.toSql { "ALTER TABLE $it" } + column.columnName.toSql { " ALTER COLUMN $it SET NOT NULL" }
    }
}

package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class AddNotNullSqlServerTemplate(db: EffectiveSettings) : DbAwareTemplate<AddNotNull>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }

    override fun convertSingle(): AddNotNull.() -> String? = {
        table.toSql { "ALTER TABLE $it" } + column.toSql { " ALTER COLUMN $it" }
    }
}

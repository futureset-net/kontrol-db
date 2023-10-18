package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds=[SqlTemplate::class])
class AddNotNullSqlServerTemplate(db: EffectiveSettings) : DbAwareTemplate<AddNotNull>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }

    override fun convertToSingleStatement(change: AddNotNull): String {
        return """
${change.table.toSql{"ALTER TABLE $it ALTER COLUMN ${change.column.toSql()}"}}
        """.trimIndent()
    }
}

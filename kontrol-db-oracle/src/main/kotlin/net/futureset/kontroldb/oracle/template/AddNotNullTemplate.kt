package net.futureset.kontroldb.oracle.template

import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class AddNotNullTemplate(db: EffectiveSettings) : DbAwareTemplate<AddNotNull>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }
    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun convertToSingleStatement(change: AddNotNull): String {
        return """
${change.table.toSql{"ALTER TABLE $it MODIFY ${change.column.columnName.toSql()} NOT NULL"}}
        """.trimIndent()
    }
}

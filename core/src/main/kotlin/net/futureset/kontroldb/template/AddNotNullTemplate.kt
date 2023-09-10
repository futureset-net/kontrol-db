package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class AddNotNullTemplate(private val db: EffectiveSettings) : DbAwareTemplate<AddNotNull>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }

    override fun convertToSingleStatement(change: AddNotNull): String {
        return """
${change.table.toSql{"ALTER TABLE $it ALTER COLUMN ${change.column.columnName.toSql()} SET NOT NULL"}}
        """.trimIndent()
    }
}

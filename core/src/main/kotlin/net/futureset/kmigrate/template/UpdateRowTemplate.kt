package net.futureset.kmigrate.template

import net.futureset.kmigrate.DbAwareTemplate
import net.futureset.kmigrate.TemplatePriority
import net.futureset.kmigrate.modelchange.UpdateRow
import net.futureset.kmigrate.settings.EffectiveSettings
import kotlin.reflect.KClass

class UpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<UpdateRow>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<UpdateRow> {
        return UpdateRow::class
    }

    override fun convert(change: UpdateRow): String {
        return """
UPDATE ${change.table.toSql()}
${change.columnValues.entries.joinToString(prefix = "SET ", separator = ", ")
            {it.key.toSql() + " = " + it.value.toSql() }}            
WHERE 
${change.whereColumnsValues.entries.joinToString(separator = " AND ")
            {it.key.toSql() + " = " + it.value.toSql() }}
        """.trimIndent()
    }
}

package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.UpdateRow
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class UpdateRowTemplate(private val db: EffectiveSettings) : DbAwareTemplate<UpdateRow>(db, TemplatePriority.DEFAULT) {
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
            {it.key.toSql() + " = " + it.value.toSql() }}${db.statementSeparator}            
        """.trimIndent()
    }
}

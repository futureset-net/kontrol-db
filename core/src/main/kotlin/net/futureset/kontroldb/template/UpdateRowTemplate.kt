package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Update
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class UpdateRowTemplate(private val db: EffectiveSettings) : DbAwareTemplate<Update>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<Update> {
        return Update::class
    }

    override fun convertToSingleStatement(change: Update): String {
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

package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Insert
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class InsertTemplate(db: EffectiveSettings) : DbAwareTemplate<Insert>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<Insert> {
        return Insert::class
    }

    override fun convertToSingleStatement(change: Insert): String {
        if (change.fromSelect != null) {
            return """
             INSERT INTO ${change.table.toSql()}(
             ${forEach(change.fromSelect.columns.map { it.columnName })}
             )      
             ${otherTemplate(change.fromSelect)} 
            """.trimIndent()
        } else {
            return """
            INSERT INTO ${change.table.toSql()}(
            ${forEach(change.columnValues.first().keys)}
            )            
            VALUES
            ${change.columnValues.joinToString(separator = "),\n(", prefix = "(", postfix = ")") {row -> forEach(row.values) }}
            """.trimIndent()
        }
    }
}

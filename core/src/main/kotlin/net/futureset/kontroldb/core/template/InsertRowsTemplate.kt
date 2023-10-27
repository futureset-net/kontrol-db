package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InsertRowsTemplate(db: EffectiveSettings) : DbAwareTemplate<InsertRows>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<InsertRows> {
        return InsertRows::class
    }

    override fun convertToSingleStatement(change: InsertRows): String {
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

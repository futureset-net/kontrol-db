package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InsertRowsTemplate(db: EffectiveSettings) : DbAwareTemplate<InsertRows>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<InsertRows> {
        return InsertRows::class
    }

    override fun convertSingle(): InsertRows.() -> String? = {
        if (fromSelect != null) {
            """
                INSERT INTO ${table.toSql()}
                (${forEach(fromSelect.columns.map { it.columnName })})      
                ${otherTemplate(fromSelect)}
            """.trimIndent()
        } else {
            """
                INSERT INTO ${table.toSql()}
                    (${forEach(columnValues.first().keys)})            
                VALUES
                    ${
                columnValues.joinToString(
                    separator = "),\n                (",
                    prefix = "(",
                    postfix = ")",
                ) { row -> forEach(row.values) }
            }
            """.trimIndent()
        }
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class InsertRowsGenerator(es: EffectiveSettings) : DbAwareGenerator<InsertRows>(es, InsertRows::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): InsertRows.() -> String? = {
        if (fromSelect != null) {
            """
                INSERT INTO ${table.toQuoted()}
                (${joinQuotableValues(fromSelect.columns.map { it.columnName })})      
                ${generateSqlSingle(fromSelect)}
            """.trimIndent()
        } else {
            """
                INSERT INTO ${table.toQuoted()}
                    (${joinQuotableValues(columnValues.first().keys)})            
                VALUES
                    ${
                columnValues.joinToString(
                    separator = "),\n                (",
                    prefix = "(",
                    postfix = ")",
                ) { row -> joinQuotableValues(row.values) }
            }
            """.trimIndent()
        }
    }
}

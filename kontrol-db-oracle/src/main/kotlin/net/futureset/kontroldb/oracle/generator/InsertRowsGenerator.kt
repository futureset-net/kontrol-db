package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InsertRowsGenerator(db: EffectiveSettings) : DbAwareGenerator<InsertRows>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<InsertRows> {
        return InsertRows::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun convertSingle(): InsertRows.() -> String? = {
        if (fromSelect != null) {
            """
                INSERT INTO ${table.toQuoted()}
                (${fromSelect!!.columns.map { it.columnName }.columnNames()})      
                ${generateSqlSingle(fromSelect!!)}
            """.trimIndent()
        } else if (columnValues.size < 2) {
            """
                INSERT INTO ${table.toQuoted()}
                    (${columnValues.first().keys.columnNames()})            
                VALUES
                    ${
                columnValues.joinToString(
                    separator = "),\n                (",
                    prefix = "(",
                    postfix = ")",
                ) { row -> joinQuotableValues(row.values) }
            }
            """.trimIndent()
        } else {
            "INSERT ALL\n" +
                columnValues.joinToString(separator = "\n") { row ->
                    "    INTO ${table.toQuoted()} (${row.keys.columnNames()}) VALUES (${joinQuotableValues(row.values)} )"
                } + "\nSELECT * FROM DUAL"
        }
    }
}

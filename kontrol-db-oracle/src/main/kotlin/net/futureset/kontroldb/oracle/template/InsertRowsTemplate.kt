package net.futureset.kontroldb.oracle.template

import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InsertRowsTemplate(db: EffectiveSettings) : DbAwareTemplate<InsertRows>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<InsertRows> {
        return InsertRows::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun convertSingle(): InsertRows.() -> String? = {
        if (fromSelect != null) {
            """
                INSERT INTO ${table.toSql()}
                (${fromSelect!!.columns.map { it.columnName }.columnNames()})      
                ${otherTemplate(fromSelect!!)}
            """.trimIndent()
        } else if (columnValues.size < 2) {
            """
                INSERT INTO ${table.toSql()}
                    (${columnValues.first().keys.columnNames()})            
                VALUES
                    ${
                columnValues.joinToString(
                    separator = "),\n                (",
                    prefix = "(",
                    postfix = ")",
                ) { row -> forEach(row.values) }
            }
            """.trimIndent()
        } else {
            "INSERT ALL\n" +
                columnValues.joinToString(separator = "\n") { row ->
                    "    INTO ${table.toSql()} (${row.keys.columnNames()}) VALUES (${forEach(row.values)} )"
                } + "\nSELECT * FROM DUAL"
        }
    }
}

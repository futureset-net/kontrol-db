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

    override fun convertToSingleStatement(change: InsertRows): String {
        return if (change.fromSelect != null) {
            """
                INSERT INTO ${change.table.toSql()}
                (${forEach(change.fromSelect!!.columns.map { it.columnName })})      
                ${otherTemplate(change.fromSelect!!)}
            """.trimIndent()
        } else if (change.columnValues.size < 2) {
            """
                INSERT INTO ${change.table.toSql()}
                    (${forEach(change.columnValues.first().keys)})            
                VALUES
                    ${change.columnValues.joinToString(separator = "),\n                (", prefix = "(", postfix = ")") {row -> forEach(row.values) }}
            """.trimIndent()
        } else {
            "INSERT ALL\n" +
                change.columnValues.joinToString(separator = "\n") { row ->
                    "    INTO ${change.table.toSql()} (${forEach(row.keys)}) VALUES (${forEach(row.values)} )"
                } + "\nSELECT * FROM DUAL"
        }
    }
}

package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InsertOrUpdateRow
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InsertOrUpdateRowGenerator(db: EffectiveSettings) :
    DbAwareGenerator<InsertOrUpdateRow>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<InsertOrUpdateRow> {
        return InsertOrUpdateRow::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun convertSingle(): InsertOrUpdateRow.() -> String? = {
        val columnNames = columnValues.first().keys
        val lines = mutableListOf(
            """
        MERGE INTO ${table.toQuoted()} s
        USING ( 
            ${columnValues.joinToString(separator = "\n UNION ALL\n") { row -> "SELECT " + row.entries.joinToString { "${it.value.toQuoted()} ${it.key.toQuoted()}" } + " FROM DUAL" }}
        ) t ON (${joinQuotableValues(primaryKeys, separateBy = " AND ") { "s.$it = t.$it" }})
            """.trimIndent(),
        )
        if (updateMode != UpdateMode.INSERT) {
            lines.add(
                "WHEN MATCHED THEN UPDATE SET ${
                    joinQuotableValues(columnNames.filterNot { it in primaryKeys }) { col ->
                        col.let { "$it = " } + col.let { "t.$it" }
                    }
                }",
            )
        }
        if (updateMode != UpdateMode.UPDATE) {
            lines.add("WHEN NOT MATCHED THEN INSERT (${joinQuotableValues(columnNames)}) VALUES (${joinQuotableValues(columnNames) { "t.$it" }})")
        }
        lines.joinToString("\n")
    }
}

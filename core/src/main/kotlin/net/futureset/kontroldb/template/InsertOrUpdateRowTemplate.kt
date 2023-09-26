package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.modelchange.InsertOrUpdateRow
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class InsertOrUpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<InsertOrUpdateRow>(db) {
    override fun type(): KClass<InsertOrUpdateRow> {
        return InsertOrUpdateRow::class
    }

    override fun convertToSingleStatement(change: InsertOrUpdateRow): String {
        val columnNames = change.columnValues.first().keys
        val lines = mutableListOf(
            """MERGE INTO ${change.table.toSql()} AS S
        USING (VALUES 
        ${change.columnValues.joinToString(separator = "),\n(", prefix = "(", postfix = ")") {row -> forEach(row.values) }}
        ) AS T (${forEach(columnNames)})
        ON ${forEach(change.primaryKeys, separateBy = " AND ") {"S.${it.toSql()} = T.${it.toSql()}"}}
            """.trimIndent(),
        )
        if (change.updateMode != UpdateMode.INSERT) {
            lines.add("WHEN MATCHED THEN UPDATE SET ${forEach(columnNames.filterNot { it in change.primaryKeys }) {col -> col.toSql().let{"S.$it = "} + col.toSql().let{"T.$it"}}}")
        }
        if (change.updateMode != UpdateMode.UPDATE) {
            lines.add("WHEN NOT MATCHED THEN INSERT (${forEach(columnNames)}) VALUES (${forEach(columnNames){"T.${it.toSql()}"}})")
        }
        return lines.joinToString("\n")
    }
}

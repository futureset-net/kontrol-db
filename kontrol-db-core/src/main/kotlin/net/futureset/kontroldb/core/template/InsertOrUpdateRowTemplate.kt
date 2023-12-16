package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.InsertOrUpdateRow
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InsertOrUpdateRowTemplate(db: EffectiveSettings) : DbAwareTemplate<InsertOrUpdateRow>(db) {
    override fun type(): KClass<InsertOrUpdateRow> {
        return InsertOrUpdateRow::class
    }

    override fun convertToSingleStatement(change: InsertOrUpdateRow): String {
        val columnNames = change.columnValues.first().keys
        val lines = mutableListOf(
            """
        MERGE INTO ${change.table.toSql()} s
        USING (VALUES 
            ${change.columnValues.joinToString(separator = "),\n            (", prefix = "(", postfix = ")") {row -> forEach(row.values) }}
        ) AS t (${forEach(columnNames)})
        ON ${forEach(change.primaryKeys, separateBy = " AND ") {"s.${it.toSql()} = t.${it.toSql()}"}}
            """.trimIndent(),
        )
        if (change.updateMode != UpdateMode.INSERT) {
            lines.add("WHEN MATCHED THEN UPDATE SET ${forEach(columnNames.filterNot { it in change.primaryKeys }) {col -> col.toSql().let{"$it = "} + col.toSql().let{"t.$it"}}}")
        }
        if (change.updateMode != UpdateMode.UPDATE) {
            lines.add("WHEN NOT MATCHED THEN INSERT (${forEach(columnNames)}) VALUES (${forEach(columnNames){"t.${it.toSql()}"}})")
        }
        return lines.joinToString("\n")
    }
}

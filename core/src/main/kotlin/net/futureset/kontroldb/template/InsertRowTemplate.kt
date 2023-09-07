package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.InsertRow
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class InsertRowTemplate(private val db: EffectiveSettings) : DbAwareTemplate<InsertRow>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<InsertRow> {
        return InsertRow::class
    }

    override fun convert(change: InsertRow): String {
        return """
INSERT INTO ${change.table.toSql()}(
${forEach(change.columnValues.keys, separateBy = ", ")}
)            
VALUES (
${forEach(change.columnValues.values, separateBy = ", ")}
)${db.statementSeparator}
        """.trimIndent()
    }
}

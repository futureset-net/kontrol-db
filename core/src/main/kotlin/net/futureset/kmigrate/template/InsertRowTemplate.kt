package net.futureset.kmigrate.template

import net.futureset.kmigrate.DbAwareTemplate
import net.futureset.kmigrate.TemplatePriority
import net.futureset.kmigrate.modelchange.InsertRow
import net.futureset.kmigrate.settings.EffectiveSettings
import kotlin.reflect.KClass

class InsertRowTemplate(db: EffectiveSettings) : DbAwareTemplate<InsertRow>(db, TemplatePriority.DEFAULT) {
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
)
        """.trimIndent()
    }
}

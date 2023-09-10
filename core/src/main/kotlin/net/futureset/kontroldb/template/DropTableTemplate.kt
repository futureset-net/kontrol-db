package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class DropTableTemplate(private val db: EffectiveSettings) : DbAwareTemplate<DropTable>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropTable> {
        return DropTable::class
    }

    override fun convertToSingleStatement(change: DropTable): String {
        return """
DROP TABLE ${change.table.toSql()}
        """.trimIndent()
    }
}

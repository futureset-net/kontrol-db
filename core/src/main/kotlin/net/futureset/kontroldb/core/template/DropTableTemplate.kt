package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropTableTemplate(db: EffectiveSettings) : DbAwareTemplate<DropTable>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropTable> {
        return DropTable::class
    }

    override fun convertToSingleStatement(change: DropTable): String {
        return """
DROP TABLE ${change.table.toSql()}
        """.trimIndent()
    }
}

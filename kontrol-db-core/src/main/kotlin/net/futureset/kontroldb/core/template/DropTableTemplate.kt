package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropTableTemplate(db: EffectiveSettings) : DbAwareTemplate<DropTable>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropTable> {
        return DropTable::class
    }

    override fun convertSingle(): DropTable.() -> String? = {
        table.toSql { "DROP TABLE $it" }
    }
}

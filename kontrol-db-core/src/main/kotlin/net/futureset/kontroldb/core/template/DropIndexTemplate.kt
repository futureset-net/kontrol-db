package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.DropIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropIndexTemplate(db: EffectiveSettings) : DbAwareTemplate<DropIndex>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropIndex> {
        return DropIndex::class
    }

    override fun convertSingle(): DropIndex.() -> String? = {
        index.toSql { "DROP INDEX ${if (ifExists) "IF EXISTS " else ""}$it" }
    }
}

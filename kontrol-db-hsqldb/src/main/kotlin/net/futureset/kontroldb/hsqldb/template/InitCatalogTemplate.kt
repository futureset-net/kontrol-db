package net.futureset.kontroldb.hsqldb.template

import net.futureset.kontroldb.modelchange.InitCatalog
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InitCatalogTemplate(effectiveSettings: EffectiveSettings) :
    DbAwareTemplate<InitCatalog>(effectiveSettings, TemplatePriority.DATABASE) {
    override fun type(): KClass<InitCatalog> = InitCatalog::class
    override fun convertSingle(): InitCatalog.() -> String? = {
        catalog.toSql { "ALTER CATALOG PUBLIC RENAME TO $it" }
    }
}

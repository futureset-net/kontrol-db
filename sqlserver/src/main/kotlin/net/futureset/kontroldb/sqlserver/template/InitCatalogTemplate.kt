package net.futureset.kontroldb.sqlserver.template

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
    override fun convert(change: InitCatalog): List<String> {
        return listOf(
            "USE ${change.catalog.toSql()}",
        )
    }
    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

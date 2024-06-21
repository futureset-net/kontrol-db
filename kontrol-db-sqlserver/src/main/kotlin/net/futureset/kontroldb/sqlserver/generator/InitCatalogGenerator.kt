package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitCatalog
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InitCatalogGenerator(es: EffectiveSettings) : DbAwareGenerator<InitCatalog>(es) {

    override fun type(): KClass<InitCatalog> = InitCatalog::class
    override fun convert(change: InitCatalog): List<String> {
        return listOf(
            change.catalog.toQuoted { "USE $it" },
        )
    }
    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"
}

package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitCatalog
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class InitCatalogGenerator(es: EffectiveSettings) : DbAwareGenerator<InitCatalog>(es, InitCatalog::class) {

    override fun convertSingle(): InitCatalog.() -> String? = {
        catalog.toQuoted { "ALTER CATALOG PUBLIC RENAME TO $it" }
    }
}

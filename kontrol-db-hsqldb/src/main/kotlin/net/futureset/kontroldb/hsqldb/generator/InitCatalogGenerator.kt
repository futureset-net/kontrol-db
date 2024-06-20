package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitCatalog
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InitCatalogGenerator(effectiveSettings: EffectiveSettings) :
    DbAwareGenerator<InitCatalog>(effectiveSettings, GeneratorPriority.DATABASE) {

    override fun type(): KClass<InitCatalog> = InitCatalog::class

    override fun convertSingle(): InitCatalog.() -> String? = {
        catalog.toQuoted { "ALTER CATALOG PUBLIC RENAME TO $it" }
    }
}

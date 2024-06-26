package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitCatalog
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class InitCatalogGeneratorEmpty(override val es: EffectiveSettings) : EmptyGenerator<InitCatalog>(InitCatalog::class) {
    override val priority = GeneratorPriority.DEFAULT
}

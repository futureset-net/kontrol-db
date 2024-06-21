package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class InitSchemaGeneratorEmpty(override val es: EffectiveSettings) : EmptyGenerator<InitSchema>(InitSchema::class) {
    override val priority = GeneratorPriority.DEFAULT
}

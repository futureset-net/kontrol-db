package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.EndBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class EndMigrationGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<EndBanner>(es, EndBanner::class) {
    override val priority = GeneratorPriority.DEFAULT
}

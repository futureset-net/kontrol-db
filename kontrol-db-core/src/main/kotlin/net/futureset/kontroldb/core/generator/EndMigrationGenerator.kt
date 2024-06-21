package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.EndBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class EndMigrationGenerator(db: EffectiveSettings) : DbAwareGenerator<EndBanner>(db) {

    override val priority = GeneratorPriority.DEFAULT

    override fun type(): KClass<EndBanner> = EndBanner::class
}

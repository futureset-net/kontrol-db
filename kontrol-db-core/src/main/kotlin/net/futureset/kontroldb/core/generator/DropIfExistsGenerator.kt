package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class DropIfExistsGenerator(es: EffectiveSettings) : DbAwareGenerator<DropIfExists>(es, DropIfExists::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): DropIfExists.() -> String? = {
        """DROP $objectType ${objectName.toQuoted()} IF EXISTS"""
    }
}

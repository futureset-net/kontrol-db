package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class DropIndexGenerator(es: EffectiveSettings) : DbAwareGenerator<DropIndex>(es, DropIndex::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): DropIndex.() -> String? = {
        index.toQuoted { "DROP INDEX ${if (ifExists) "IF EXISTS " else ""}$it" }
    }
}

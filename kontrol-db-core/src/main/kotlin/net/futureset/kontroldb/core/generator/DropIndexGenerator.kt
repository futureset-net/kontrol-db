package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DropIndexGenerator(db: EffectiveSettings) : DbAwareGenerator<DropIndex>(db) {

    override val priority = GeneratorPriority.DEFAULT

    override fun type(): KClass<DropIndex> {
        return DropIndex::class
    }

    override fun convertSingle(): DropIndex.() -> String? = {
        index.toQuoted { "DROP INDEX ${if (ifExists) "IF EXISTS " else ""}$it" }
    }
}

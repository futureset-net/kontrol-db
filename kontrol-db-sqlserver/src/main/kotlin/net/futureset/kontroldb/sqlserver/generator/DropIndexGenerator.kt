package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DropIndexGenerator(db: EffectiveSettings) : DbAwareGenerator<DropIndex>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<DropIndex> {
        return DropIndex::class
    }

    override fun convertSingle(): DropIndex.() -> String? = {
        index.name.toQuoted { "DROP INDEX ${if (ifExists) "IF EXISTS " else ""}$it ON ${table.toQuoted()}" }
    }
}

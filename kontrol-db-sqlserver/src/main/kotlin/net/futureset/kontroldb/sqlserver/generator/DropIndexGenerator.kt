package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class DropIndexGenerator(es: EffectiveSettings) : DbAwareGenerator<DropIndex>(es, DropIndex::class) {

    override fun convertSingle(): DropIndex.() -> String? = {
        index.name.toQuoted { "DROP INDEX ${if (ifExists) "IF EXISTS " else ""}$it ON ${table.toQuoted()}" }
    }
}

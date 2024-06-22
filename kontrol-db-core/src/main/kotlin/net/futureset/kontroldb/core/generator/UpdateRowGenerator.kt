package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class UpdateRowGenerator(es: EffectiveSettings) : DbAwareGenerator<UpdateRows>(es, UpdateRows::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): UpdateRows.() -> String? = {
        table.toQuoted { "UPDATE $it SET " } +
            joinQuotableValues(columnValues) +
            predicate.takeUnless { it.isEmpty() }.toQuoted { " WHERE $it" }
    }
}

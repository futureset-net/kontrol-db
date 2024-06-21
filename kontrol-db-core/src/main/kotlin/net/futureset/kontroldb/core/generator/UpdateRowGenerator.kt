package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class UpdateRowGenerator(db: EffectiveSettings) : DbAwareGenerator<UpdateRows>(db) {

    override val priority = GeneratorPriority.DEFAULT

    override fun type(): KClass<UpdateRows> {
        return UpdateRows::class
    }

    override fun convertSingle(): UpdateRows.() -> String? = {
        table.toQuoted { "UPDATE $it SET " } +
            joinQuotableValues(columnValues) +
            predicate.takeUnless { it.isEmpty() }.toQuoted { " WHERE $it" }
    }
}

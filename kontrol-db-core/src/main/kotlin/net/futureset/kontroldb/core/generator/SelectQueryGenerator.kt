package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class SelectQueryGenerator(db: EffectiveSettings) :
    DbAwareGenerator<SelectQuery>(db) {

    override val priority = GeneratorPriority.DEFAULT

    override fun type(): KClass<SelectQuery> {
        return SelectQuery::class
    }

    override fun convertSingle(): SelectQuery.() -> String? = {
        "SELECT " + joinQuotableValues(columns, separateBy = ",\n        ") +
            table.toQuoted { " FROM $it" } +
            predicate.toQuoted { " WHERE $it" }
    }
}

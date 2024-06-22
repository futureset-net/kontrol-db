package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class SelectQueryGenerator(db: EffectiveSettings) :
    DbAwareGenerator<SelectQuery>(db, SelectQuery::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): SelectQuery.() -> String? = {
        "SELECT " + joinQuotableValues(columns, separateBy = ",\n        ") +
            table.toQuoted { " FROM $it" } +
            predicate.toQuoted { " WHERE $it" }
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ExportQuery
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class ExportQueryGenerator(db: EffectiveSettings) :
    DbAwareGenerator<ExportQuery>(db, ExportQuery::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): ExportQuery.() -> String? = {
        """
SELECT
${joinQuotableValues(selectQuery.columns, separateBy = ",\n    ")}
${selectQuery.table.toQuoted { "FROM $it" }}
${selectQuery.predicate.toQuoted { "WHERE $it" }}
        """.trimIndent()
    }
}

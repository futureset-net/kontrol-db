package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.trimBlankLines
import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class UpdateRowGenerator(es: EffectiveSettings) : DbAwareGenerator<UpdateRows>(es, UpdateRows::class) {

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"

    override fun convertSingle(): UpdateRows.() -> String? = {
        """
            UPDATE ${table.alias ?: table.table.toQuoted()}
            SET ${joinQuotableValues(columnValues)}         
            ${if (table.alias != null) table.toQuoted { " FROM $it" } else ""}
            ${predicate.takeUnless { it.isEmpty() }.toQuoted { "WHERE $it" }}
        """.trimIndent().trimBlankLines()
    }
}

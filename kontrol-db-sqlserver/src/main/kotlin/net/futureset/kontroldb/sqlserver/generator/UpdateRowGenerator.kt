package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.trimBlankLines
import net.futureset.kontroldb.modelchange.UpdateRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class UpdateRowGenerator(db: EffectiveSettings) : DbAwareGenerator<UpdateRows>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<UpdateRows> {
        return UpdateRows::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean =
        effectiveSettings.databaseName == "sqlserver"

    override fun convertSingle(): UpdateRows.() -> String? = {
        """
            UPDATE ${table.alias ?: table.table.toQuoted()}
            SET ${joinQuotableValues(columnValues)}         
            ${if (table.alias != null) table.toQuoted { " FROM $it" } else ""}
            ${predicate.takeUnless { it.isEmpty() }.toQuoted { "WHERE $it" }}
        """.trimIndent().trimBlankLines()
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class DeleteRowsGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<DeleteRows>(es, DeleteRows::class) {
    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): DeleteRows.() -> String? = {
        """
            DELETE FROM ${table.toQuoted()}
            ${predicate.toQuoted { "WHERE $it" }}
        """.trimIndent()
    }
}

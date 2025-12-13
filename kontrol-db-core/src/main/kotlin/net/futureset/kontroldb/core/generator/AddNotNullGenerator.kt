package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class AddNotNullGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<AddNotNull>(es, AddNotNull::class) {
    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): AddNotNull.() -> String? = {
        table.toQuoted { "ALTER TABLE $it" } + column.columnName.toQuoted { " ALTER COLUMN $it SET NOT NULL" }
    }
}

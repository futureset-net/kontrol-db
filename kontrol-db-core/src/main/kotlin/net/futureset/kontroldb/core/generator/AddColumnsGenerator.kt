package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddColumns
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class AddColumnsGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<AddColumns>(es, AddColumns::class) {
    override val priority = GeneratorPriority.DEFAULT

    override fun convert(change: AddColumns): List<String> = change.columnDefinitions
        .map { col -> change.table.toQuoted { "ALTER TABLE $it ADD ${col.toQuoted()}" } }
}

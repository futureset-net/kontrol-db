package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.RenameColumn
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class RenameColumnGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<RenameColumn>(es, RenameColumn::class) {
    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): RenameColumn.() -> String? = {
        table.toQuoted { "ALTER TABLE $it RENAME COLUMN ${oldName.toQuoted(es)} TO ${newName.toQuoted(es)}" }
    }
}

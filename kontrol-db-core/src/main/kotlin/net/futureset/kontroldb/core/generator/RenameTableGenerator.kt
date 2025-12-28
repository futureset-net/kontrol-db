package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.RenameTable
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class RenameTableGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<RenameTable>(es, RenameTable::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): RenameTable.() -> String? = {
        val renameTo = to.toQuoted(es)
        from.toQuoted { "ALTER TABLE $it RENAME TO $renameTo" }
    }
}

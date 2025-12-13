package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class DropTableGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<DropTable>(es, DropTable::class) {
    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): DropTable.() -> String? = {
        table.toQuoted { "DROP TABLE $it" }
    }
}

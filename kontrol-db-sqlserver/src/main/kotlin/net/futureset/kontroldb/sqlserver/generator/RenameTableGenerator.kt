package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.RenameTable
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class RenameTableGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<RenameTable>(es, RenameTable::class) {
    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"

    override fun convertSingle(): RenameTable.() -> String? = {
        val qualified = from.toQuoted(es)
        "EXEC sp_rename $qualified, ${to.toQuoted(es)}"
    }
}

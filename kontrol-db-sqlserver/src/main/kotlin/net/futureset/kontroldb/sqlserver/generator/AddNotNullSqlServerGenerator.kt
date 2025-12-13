package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class AddNotNullSqlServerGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<AddNotNull>(es, AddNotNull::class) {
    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"

    override fun convertSingle(): AddNotNull.() -> String? = {
        table.toQuoted { "ALTER TABLE $it" } + column.toQuoted { " ALTER COLUMN $it" }
    }
}

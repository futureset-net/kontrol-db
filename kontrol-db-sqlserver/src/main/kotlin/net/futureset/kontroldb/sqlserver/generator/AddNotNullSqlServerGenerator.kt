package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddNotNullSqlServerGenerator(es: EffectiveSettings) : DbAwareGenerator<AddNotNull>(es) {

    override fun type(): KClass<AddNotNull> = AddNotNull::class

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"

    override fun convertSingle(): AddNotNull.() -> String? = {
        table.toQuoted { "ALTER TABLE $it" } + column.toQuoted { " ALTER COLUMN $it" }
    }
}

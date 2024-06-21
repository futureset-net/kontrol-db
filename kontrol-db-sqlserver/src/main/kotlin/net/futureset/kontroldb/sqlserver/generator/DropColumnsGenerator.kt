package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropColumns
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DropColumnsGenerator(es: EffectiveSettings) : DbAwareGenerator<DropColumns>(es) {

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"

    override fun type(): KClass<DropColumns> = DropColumns::class

    override fun convertSingle(): DropColumns.() -> String? = {
        table.toQuoted { tab -> "ALTER TABLE $tab DROP COLUMN ${columns.columnNames()}" }
    }
}

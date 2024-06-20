package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropColumns
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DropColumnsGenerator(db: EffectiveSettings) : DbAwareGenerator<DropColumns>(
    db,
    GeneratorPriority.DATABASE,
) {

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean =
        effectiveSettings.databaseName == "sqlserver"

    override fun type(): KClass<DropColumns> {
        return DropColumns::class
    }

    override fun convertSingle(): DropColumns.() -> String? = {
        table.toQuoted { tab -> "ALTER TABLE $tab DROP COLUMN ${columns.columnNames()}" }
    }
}

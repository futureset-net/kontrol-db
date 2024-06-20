package net.futureset.kontroldb.core.generator

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
    GeneratorPriority.DEFAULT,
) {
    override fun type(): KClass<DropColumns> {
        return DropColumns::class
    }

    override fun convert(change: DropColumns): List<String> = change.columns
        .map { col -> change.table.toQuoted { "ALTER TABLE $it" } + col.toQuoted { " DROP COLUMN $it" } }
}

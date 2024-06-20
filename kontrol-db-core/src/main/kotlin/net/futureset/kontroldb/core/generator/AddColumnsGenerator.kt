package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddColumns
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddColumnsGenerator(db: EffectiveSettings) : DbAwareGenerator<AddColumns>(
    db,
    GeneratorPriority.DEFAULT,
) {
    override fun type(): KClass<AddColumns> {
        return AddColumns::class
    }

    override fun convert(change: AddColumns): List<String> {
        return change.columnDefinitions
            .map { col -> change.table.toQuoted { "ALTER TABLE $it ADD ${col.toQuoted()}" } }
    }
}

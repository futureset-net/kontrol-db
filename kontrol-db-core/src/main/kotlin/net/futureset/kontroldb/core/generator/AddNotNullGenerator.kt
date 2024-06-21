package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddNotNullGenerator(db: EffectiveSettings) : DbAwareGenerator<AddNotNull>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }

    override fun convertSingle(): AddNotNull.() -> String? = {
        table.toQuoted { "ALTER TABLE $it" } + column.columnName.toQuoted { " ALTER COLUMN $it SET NOT NULL" }
    }
}

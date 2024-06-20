package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddForeignKey
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddForeignKeyGenerator(db: EffectiveSettings) : DbAwareGenerator<AddForeignKey>(
    db,
    GeneratorPriority.DEFAULT,
) {
    override fun type(): KClass<AddForeignKey> {
        return AddForeignKey::class
    }

    override fun convertSingle(): AddForeignKey.() -> String? = {
        table.toQuoted { "ALTER TABLE $it ADD" } +
            constraintName.toQuoted { " CONSTRAINT $it FOREIGN KEY(" } +
            joinQuotableValues(columnMap.keys) +
            foreignTable.toQuoted { ") REFERENCES $it(" } + joinQuotableValues(columnMap.values) + ")"
    }
}

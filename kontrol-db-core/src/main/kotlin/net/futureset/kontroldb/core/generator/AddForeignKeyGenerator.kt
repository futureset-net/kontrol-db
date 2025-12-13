package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddForeignKey
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class AddForeignKeyGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<AddForeignKey>(es, AddForeignKey::class) {
    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): AddForeignKey.() -> String? = {
        table.toQuoted { "ALTER TABLE $it ADD" } +
            constraintName.toQuoted { " CONSTRAINT $it FOREIGN KEY(" } +
            joinQuotableValues(columnMap.keys) +
            foreignTable.toQuoted { ") REFERENCES $it(" } + joinQuotableValues(columnMap.values) + ")"
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddPrimaryKeyGenerator(db: EffectiveSettings) : DbAwareGenerator<AddPrimaryKey>(db, GeneratorPriority.DEFAULT) {
    override fun type(): KClass<AddPrimaryKey> {
        return AddPrimaryKey::class
    }

    override fun convertSingle(): AddPrimaryKey.() -> String? = {
        table.takeUnless { inline }.toQuoted { "ALTER TABLE $it ADD" } +
            constraintName.toQuoted { " CONSTRAINT $it" } +
            " PRIMARY KEY(" + joinQuotableValues(columnReferences) + ")"
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class ChangeToDefaultCatalogAndSchemaGenerator(es: EffectiveSettings) :
    DbAwareGenerator<ChangeToDefaultCatalogAndSchema>(es, ChangeToDefaultCatalogAndSchema::class) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): ChangeToDefaultCatalogAndSchema.() -> String? = {
        es.defaultSchema?.toQuoted { "SET SCHEMA $it" }
    }
}

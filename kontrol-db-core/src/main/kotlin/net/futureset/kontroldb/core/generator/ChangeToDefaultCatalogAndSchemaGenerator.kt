package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class ChangeToDefaultCatalogAndSchemaGenerator(val db: EffectiveSettings) : DbAwareGenerator<ChangeToDefaultCatalogAndSchema>(db, GeneratorPriority.DEFAULT) {

    override fun type(): KClass<ChangeToDefaultCatalogAndSchema> = ChangeToDefaultCatalogAndSchema::class

    override fun convertSingle(): ChangeToDefaultCatalogAndSchema.() -> String? = {
        db.defaultSchema?.toQuoted { "SET SCHEMA $it" }
    }
}

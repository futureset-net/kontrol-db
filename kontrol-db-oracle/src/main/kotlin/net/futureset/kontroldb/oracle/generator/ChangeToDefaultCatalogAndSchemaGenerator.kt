package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class ChangeToDefaultCatalogAndSchemaGenerator(val db: EffectiveSettings) :
    DbAwareGenerator<ChangeToDefaultCatalogAndSchema>(db, GeneratorPriority.DATABASE) {

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "oracle"

    override fun type(): KClass<ChangeToDefaultCatalogAndSchema> = ChangeToDefaultCatalogAndSchema::class

    override fun convertSingle(): ChangeToDefaultCatalogAndSchema.() -> String? = {
        db.defaultSchema?.toQuoted { "ALTER SESSION SET CURRENT_SCHEMA=$it" }
    }
}

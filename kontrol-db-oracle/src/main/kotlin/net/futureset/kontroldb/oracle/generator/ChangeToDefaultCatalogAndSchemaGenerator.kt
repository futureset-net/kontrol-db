package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class ChangeToDefaultCatalogAndSchemaGenerator(es: EffectiveSettings) :
    DbAwareGenerator<ChangeToDefaultCatalogAndSchema>(es, ChangeToDefaultCatalogAndSchema::class) {

    override fun canApplyTo(es: EffectiveSettings): Boolean = this.es.databaseName == "oracle"

    override fun convertSingle(): ChangeToDefaultCatalogAndSchema.() -> String? = {
        es.defaultSchema?.toQuoted { "ALTER SESSION SET CURRENT_SCHEMA=$it" }
    }
}

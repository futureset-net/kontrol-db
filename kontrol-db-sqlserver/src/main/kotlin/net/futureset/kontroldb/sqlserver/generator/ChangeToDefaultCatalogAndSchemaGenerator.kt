package net.futureset.kontroldb.sqlserver.generator

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

    override fun type(): KClass<ChangeToDefaultCatalogAndSchema> = ChangeToDefaultCatalogAndSchema::class
    override fun convert(change: ChangeToDefaultCatalogAndSchema): List<String> {
        return listOfNotNull(
            db.defaultCatalog?.toQuoted { "USE $it" },
            db.defaultSchema?.name?.let { "IF schema_name()<>'$it' RAISERROR('The default schema of the user ${db.username} is not $it',16,1)" },
        )
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

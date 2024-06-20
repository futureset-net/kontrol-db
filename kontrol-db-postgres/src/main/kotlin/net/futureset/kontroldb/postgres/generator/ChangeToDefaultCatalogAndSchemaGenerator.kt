package net.futureset.kontroldb.postgres.generator

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
            db.defaultCatalog?.name?.let {
                """
                do $$
                begin
                    if not exists (select 1 where "current_database"()='$it') then
                        raise 'The default database of the user ${db.username} is not $it';
                    end if;    
                end $$
                """.trimIndent()
            },
            db.defaultSchema?.name?.let {
                """
                do $$
                begin
                    if not exists (select 1 where current_schema()='$it') then
                        raise 'The default schema of the user ${db.username} is not $it';
                    end if;    
                end $$
                """.trimIndent()
            },
        )
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "postgres"
    }
}

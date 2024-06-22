package net.futureset.kontroldb.postgres.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class ChangeToDefaultCatalogAndSchemaGenerator(es: EffectiveSettings) :
    DbAwareGenerator<ChangeToDefaultCatalogAndSchema>(es, ChangeToDefaultCatalogAndSchema::class) {

    override fun convert(change: ChangeToDefaultCatalogAndSchema): List<String> {
        return listOfNotNull(
            es.defaultCatalog?.name?.let {
                """
                do $$
                begin
                    if not exists (select 1 where "current_database"()='$it') then
                        raise 'The default database of the user ${es.username} is not $it';
                    end if;    
                end $$
                """.trimIndent()
            },
            es.defaultSchema?.name?.let {
                """
                do $$
                begin
                    if not exists (select 1 where current_schema()='$it') then
                        raise 'The default schema of the user ${es.username} is not $it';
                    end if;    
                end $$
                """.trimIndent()
            },
        )
    }

    override fun canApplyTo(es: EffectiveSettings): Boolean {
        return es.databaseName == "postgres"
    }
}

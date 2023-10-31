package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ChangeToDefaultCatalogAndSchemaTemplate(val db: EffectiveSettings) :
    DbAwareTemplate<ChangeToDefaultCatalogAndSchema>(db, TemplatePriority.DATABASE) {

    override fun type(): KClass<ChangeToDefaultCatalogAndSchema> = ChangeToDefaultCatalogAndSchema::class
    override fun convert(change: ChangeToDefaultCatalogAndSchema): List<String> {
        return listOfNotNull(
            db.defaultCatalog?.toSql { "USE $it" },
            db.defaultSchema?.name?.let { "IF schema_name()<>'$it' RAISERROR('The default schema of the user ${db.username} is not $it',16,1)" },
        )
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

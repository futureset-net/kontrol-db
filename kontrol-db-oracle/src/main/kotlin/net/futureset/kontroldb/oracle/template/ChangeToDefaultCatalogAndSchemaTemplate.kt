package net.futureset.kontroldb.oracle.template

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

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "oracle"

    override fun type(): KClass<ChangeToDefaultCatalogAndSchema> = ChangeToDefaultCatalogAndSchema::class

    override fun convertSingle(): ChangeToDefaultCatalogAndSchema.() -> String? = {
        db.defaultSchema?.toSql { "ALTER SESSION SET CURRENT_SCHEMA=$it" }
    }
}

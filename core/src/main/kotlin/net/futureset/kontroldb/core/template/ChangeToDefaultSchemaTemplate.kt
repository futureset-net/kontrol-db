package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.TemplateResolver
import net.futureset.kontroldb.modelchange.ChangeToDefaultSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ChangeToDefaultSchemaTemplate(val db: EffectiveSettings) : DbAwareTemplate<ChangeToDefaultSchema>(db, TemplatePriority.DEFAULT) {

    override lateinit var templateResolver: TemplateResolver
    override fun type(): KClass<ChangeToDefaultSchema> = ChangeToDefaultSchema::class
    override fun convertToSingleStatement(change: ChangeToDefaultSchema): String? {
        return db.defaultSchema?.toSql { "SET SCHEMA $it" }
    }
}

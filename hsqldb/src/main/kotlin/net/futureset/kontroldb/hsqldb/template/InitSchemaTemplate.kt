package net.futureset.kontroldb.hsqldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InitSchemaTemplate(effectiveSettings: EffectiveSettings) :
    DbAwareTemplate<InitSchema>(effectiveSettings, TemplatePriority.DATABASE) {
    override fun type(): KClass<InitSchema> = InitSchema::class
    override fun convertToSingleStatement(change: InitSchema): String {
        return "CREATE SCHEMA ${change.schema.toSql()}"
    }
}

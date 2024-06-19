package net.futureset.kontroldb.hsqldb.template

import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InitSchemaTemplate(override val effectiveSettings: EffectiveSettings) :
    DbAwareTemplate<InitSchema>(effectiveSettings, TemplatePriority.DATABASE) {
    override fun type(): KClass<InitSchema> = InitSchema::class

    override fun convert(change: InitSchema): List<String> {
        return listOfNotNull(
            "CREATE SCHEMA IF NOT EXISTS ${change.schema.toSql()}",
            effectiveSettings.username?.let { un -> "ALTER USER $un SET INITIAL SCHEMA ${change.schema.toSql()}" },
        )
    }

    override fun convertToSingleStatement(change: InitSchema): String {
        return "CREATE SCHEMA ${change.schema.toSql()}"
    }
}

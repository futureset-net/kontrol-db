package net.futureset.kontroldb.postgres.template

import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class InitSchemaTemplate(effectiveSettings: EffectiveSettings) :
    DbAwareTemplate<InitSchema>(effectiveSettings, TemplatePriority.DATABASE) {
    override fun type(): KClass<InitSchema> = InitSchema::class

    override fun convert(change: InitSchema): List<String> {
        return listOf(
            change.schema.toSql { "CREATE SCHEMA IF NOT EXISTS $it" },
            change.schema.toSql { "SET search_path TO $it" },
        ).filter { it.isNotBlank() }
    }
}

package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InitSchemaGenerator(override val es: EffectiveSettings) : DbAwareGenerator<InitSchema>(es) {

    override fun type(): KClass<InitSchema> = InitSchema::class

    override fun convert(change: InitSchema): List<String> {
        return listOfNotNull(
            change.schema.toQuoted { "CREATE SCHEMA IF NOT EXISTS $it" },
            es.username?.let { un -> change.schema.toQuoted { "ALTER USER $un SET INITIAL SCHEMA $it" } },
        )
    }
}

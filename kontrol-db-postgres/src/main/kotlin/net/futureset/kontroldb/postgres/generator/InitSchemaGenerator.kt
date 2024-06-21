package net.futureset.kontroldb.postgres.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InitSchemaGenerator(es: EffectiveSettings) : DbAwareGenerator<InitSchema>(es) {

    override fun type(): KClass<InitSchema> = InitSchema::class

    override fun convert(change: InitSchema): List<String> {
        return listOf(
            change.schema.toQuoted { "CREATE SCHEMA IF NOT EXISTS $it" },
            change.schema.toQuoted { "SET search_path TO $it" },
        ).filter { it.isNotBlank() }
    }
}

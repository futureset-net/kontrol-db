package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class InitSchemaGenerator(effectiveSettings: EffectiveSettings) :
    DbAwareGenerator<InitSchema>(effectiveSettings, GeneratorPriority.DATABASE) {

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun type(): KClass<InitSchema> = InitSchema::class
    override fun convert(change: InitSchema): List<String> {
        return emptyList()
    }
}

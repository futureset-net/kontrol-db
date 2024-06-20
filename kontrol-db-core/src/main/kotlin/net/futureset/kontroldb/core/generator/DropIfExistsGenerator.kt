package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DropIfExistsGenerator(db: EffectiveSettings) : DbAwareGenerator<DropIfExists>(db, GeneratorPriority.DEFAULT) {
    override fun type(): KClass<DropIfExists> = DropIfExists::class

    override fun convertSingle(): DropIfExists.() -> String? = {
        """DROP $objectType ${objectName.toQuoted()} IF EXISTS"""
    }
}

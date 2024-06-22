package net.futureset.kontroldb.postgres.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class DropIfExistsGenerator(es: EffectiveSettings) : DbAwareGenerator<DropIfExists>(es, DropIfExists::class) {

    override fun convertSingle(): DropIfExists.() -> String = {
        objectName.toQuoted { "DROP $objectType IF EXISTS $it" }
    }

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "postgres"
}

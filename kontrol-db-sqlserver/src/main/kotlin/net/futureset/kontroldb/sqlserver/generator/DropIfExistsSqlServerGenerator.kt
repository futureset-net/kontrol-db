package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DropIfExistsSqlServerGenerator(es: EffectiveSettings) : DbAwareGenerator<DropIfExists>(es) {

    override fun type(): KClass<DropIfExists> = DropIfExists::class

    override fun convertSingle(): DropIfExists.() -> String? = {
        objectName.toQuoted { "DROP $objectType IF EXISTS $it" }
    }

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"
}

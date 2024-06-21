package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddNotNullGenerator(es: EffectiveSettings) : DbAwareGenerator<AddNotNull>(es) {

    override fun type(): KClass<AddNotNull> = AddNotNull::class

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "oracle"

    override fun convertSingle(): AddNotNull.() -> String? = {
        """
${table.toQuoted { "ALTER TABLE $it MODIFY ${column.columnName.toQuoted()} NOT NULL" }}
        """.trimIndent()
    }
}

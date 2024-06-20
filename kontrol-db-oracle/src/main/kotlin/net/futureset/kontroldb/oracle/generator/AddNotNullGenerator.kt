package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class AddNotNullGenerator(db: EffectiveSettings) : DbAwareGenerator<AddNotNull>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<AddNotNull> {
        return AddNotNull::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun convertSingle(): AddNotNull.() -> String? = {
        """
${table.toQuoted { "ALTER TABLE $it MODIFY ${column.columnName.toQuoted()} NOT NULL" }}
        """.trimIndent()
    }
}

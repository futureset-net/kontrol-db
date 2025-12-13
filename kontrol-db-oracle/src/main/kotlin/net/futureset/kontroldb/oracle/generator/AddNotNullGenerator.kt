package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class AddNotNullGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<AddNotNull>(es, AddNotNull::class) {
    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "oracle"

    override fun convertSingle(): AddNotNull.() -> String? = {
        """
${table.toQuoted { "ALTER TABLE $it MODIFY ${column.columnName.toQuoted()} NOT NULL" }}
        """.trimIndent()
    }
}

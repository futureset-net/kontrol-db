package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DeleteRowsGenerator(db: EffectiveSettings) : DbAwareGenerator<DeleteRows>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun type(): KClass<DeleteRows> {
        return DeleteRows::class
    }

    override fun convertSingle(): DeleteRows.() -> String? = {
        """
    DELETE FROM ${table.toQuoted()}
    ${predicate.toQuoted { "WHERE $it" }}
        """.trimIndent()
    }
}

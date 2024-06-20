package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class DeleteRowGenerator(db: EffectiveSettings) : DbAwareGenerator<DeleteRows>(db, GeneratorPriority.DEFAULT) {
    override fun type(): KClass<DeleteRows> {
        return DeleteRows::class
    }

    override fun convertSingle(): DeleteRows.() -> String? = {
        """
DELETE ${table.alias ?: "X"}   
FROM ${table.copy(alias = table.alias ?: "X").toQuoted()}        
${predicate.toQuoted { "WHERE $it" }}         
        """.trimIndent()
    }
}

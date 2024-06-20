package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class CreateIndexGenerator(private val db: EffectiveSettings) :
    DbAwareGenerator<CreateIndex>(db, GeneratorPriority.DEFAULT) {
    override fun type(): KClass<CreateIndex> {
        return CreateIndex::class
    }

    override fun convertSingle(): CreateIndex.() -> String? = {
        """
CREATE ${
            "UNIQUE ".takeIf { unique }.orEmpty()
        }INDEX ${indexName.toQuoted()} ON ${table.toQuoted()}(${joinQuotableValues(columnReferences)}) ${(tablespace ?: db.defaultIndexTablespace).toQuoted { " TABLESPACE $it " }}
        """.trimIndent()
    }
}

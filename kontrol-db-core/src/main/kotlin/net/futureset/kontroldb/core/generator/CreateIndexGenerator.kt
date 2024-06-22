package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class CreateIndexGenerator(private val db: EffectiveSettings) :
    DbAwareGenerator<CreateIndex>(db, CreateIndex::class) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): CreateIndex.() -> String? = {
        """
CREATE ${
            "UNIQUE ".takeIf { unique }.orEmpty()
        }INDEX ${indexName.toQuoted()} ON ${table.toQuoted()}(${joinQuotableValues(columnReferences)}) ${(tablespace ?: db.defaultIndexTablespace).toQuoted { " TABLESPACE $it " }}
        """.trimIndent()
    }
}

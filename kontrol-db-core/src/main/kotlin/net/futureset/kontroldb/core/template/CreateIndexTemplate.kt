package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.CreateIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateIndexTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<CreateIndex>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<CreateIndex> {
        return CreateIndex::class
    }

    override fun convertSingle(): CreateIndex.() -> String? = {
        """
CREATE ${
            "UNIQUE ".takeIf { unique }.orEmpty()
        }INDEX ${indexName.toSql()} ON ${table.toSql()}(${forEach(columnReferences)}) ${(tablespace ?: db.defaultIndexTablespace).toSql { " TABLESPACE $it " }}
        """.trimIndent()
    }
}

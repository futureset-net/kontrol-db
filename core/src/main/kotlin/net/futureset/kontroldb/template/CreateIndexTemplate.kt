package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.CreateIndex
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class CreateIndexTemplate(private val db: EffectiveSettings) : DbAwareTemplate<CreateIndex>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<CreateIndex> {
        return CreateIndex::class
    }

    override fun convertToSingleStatement(change: CreateIndex): String {
        return """
CREATE ${"UNIQUE ".takeIf { change.unique }.orEmpty()}INDEX ${change.indexName.toSql() } ON ${change.table.toSql()}(${forEach(change.columnReferences)}) ${(change.tablespace ?: db.defaultIndexTablespace).toSql{" TABLESPACE $it "}}
        """.trimIndent()
    }
}

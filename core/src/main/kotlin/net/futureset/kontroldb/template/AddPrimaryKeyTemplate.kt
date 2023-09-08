package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class AddPrimaryKeyTemplate(private val db: EffectiveSettings) : DbAwareTemplate<AddPrimaryKey>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<AddPrimaryKey> {
        return AddPrimaryKey::class
    }

    override fun convert(change: AddPrimaryKey): String {
        return """
ALTER TABLE ${change.table.toSql()} ADD CONSTRAINT ${change.constraintName.toSql()} PRIMARY KEY(${forEach(change.columnReferences)}) ${(change.tablespace ?: db.defaultTablespace)?.let{" TABLESPACE ${it.toSql()} "}.orEmpty()}${db.statementSeparator}
        """.trimIndent()
    }
}

package net.futureset.kmigrate.template

import net.futureset.kmigrate.DbAwareTemplate
import net.futureset.kmigrate.TemplatePriority
import net.futureset.kmigrate.modelchange.CreateTable
import net.futureset.kmigrate.settings.EffectiveSettings
import kotlin.reflect.KClass

class CreateTableTemplate(private val db: EffectiveSettings) : DbAwareTemplate<CreateTable>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun convert(change: CreateTable): String {
        return """
CREATE TABLE ${change.table.toSql()}${(change.tablespace ?: db.defaultTablespace)?.let{" TABLESPACE ${it.toSql()} "}.orEmpty()} (
    ${forEach(change.columnDefinitions, separateBy = ",\n    ")}
)
        """.trimIndent()
    }
}

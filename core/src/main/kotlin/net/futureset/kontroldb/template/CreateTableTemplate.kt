package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class CreateTableTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<CreateTable>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun convertToSingleStatement(change: CreateTable): String {
        return """
CREATE TABLE ${change.table.toSql()}${(change.tablespace ?: db.defaultTablespace).toSql { " TABLESPACE $it" }} (
    ${forEach(change.columnDefinitions, separateBy = ",\n    ")}
    ${change.primaryKey?.let{ "," + otherTemplate(it) }.orEmpty()}
)
        """.trimIndent()
    }
}

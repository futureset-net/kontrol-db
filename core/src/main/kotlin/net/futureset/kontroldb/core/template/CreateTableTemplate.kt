package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
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

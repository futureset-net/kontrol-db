package net.futureset.kontroldb.postgres.template

import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateTemporaryTableTemplate(private val db: EffectiveSettings) :
    DbAwareTemplate<CreateTable>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "postgres"
    override fun convertToSingleStatement(change: CreateTable): String {
        val colNames = change.columnDefinitions.takeUnless { it.isEmpty() }
            ?: change.fromSelect?.columns?.map { it.columnName }.orEmpty()
        val selectQuery = change.fromSelect
        return if (selectQuery != null) {
            otherTemplate(selectQuery).replaceFirst("FROM", "INTO ${change.table.toSql()}\nFROM") +
                if (selectQuery.includeData) {
                    ""
                } else if (selectQuery.predicate?.isEmpty() != false) {
                    " WHERE 1=0"
                } else {
                    "AND 1=0"
                }
        } else {
            """
            CREATE TABLE ${change.table.toSql()} (
            ${forEach(colNames, separateBy = ",\n    ")}
            ${
                change.primaryKey?.takeIf { change.table.tablePersistence == TablePersistence.NORMAL }
                    ?.let { "," + otherTemplate(it) }.orEmpty()} 
            )
            """.trimIndent()
        }
    }
}

// @Singleton(binds = [SqlTemplate::class])
// class CreateTemporaryTableTemplate(db: EffectiveSettings) :
//    DbAwareTemplate<CreateTemporaryTable>(db, TemplatePriority.DATABASE) {
//    override fun type(): KClass<CreateTemporaryTable> {
//        return CreateTemporaryTable::class
//    }
//
//    override fun convertToSingleStatement(change: CreateTemporaryTable): String {
//        val colNames =
//            change.columnDefinitions.takeUnless { it.isEmpty() } ?: change.fromSelect?.columns?.map { it.columnName }
//                .orEmpty()
//        val query = change.fromSelect
//        return if (query != null) {
//            template(query)?.convert(query)?.first()?.replaceFirst("FROM", "INTO " + tempTable(change.table, change.tablePersistence).toSql() + "\nFROM").orEmpty()
//        } else {
//            """
//             CREATE TABLE ${tempTable(change.table, change.tablePersistence).toSql()} (
//            ${forEach(colNames, separateBy = ",\n    ")})
//            """.trimIndent()
//        }
//    }
//
//    private fun tempTable(schemaObject: SchemaObject, tablePersistence: TablePersistence) =
//        schemaObject.copy(name = DbIdentifier((if (tablePersistence == TablePersistence.TEMPORARY) "#" else "##") + schemaObject.name.name))
//
//    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
//        return effectiveSettings.databaseName == "sqlserver"
//    }
// }

package net.futureset.kontroldb.postgres.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class CreateTemporaryTableGenerator(private val db: EffectiveSettings) :
    DbAwareGenerator<CreateTable>(db, GeneratorPriority.DATABASE) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = db.databaseName == "postgres"

    override fun convertSingle(): CreateTable.() -> String = {
        val colNames = columnDefinitions.takeUnless { it.isEmpty() }
            ?: fromSelect?.columns?.map { it.columnName }.orEmpty()
        val selectQuery = fromSelect
        if (selectQuery != null) {
            generateSqlSingle(selectQuery).replaceFirst("FROM", "INTO ${table.toQuoted()}\nFROM") +
                if (selectQuery.includeData) {
                    ""
                } else if (selectQuery.predicate?.isEmpty() != false) {
                    " WHERE 1=0"
                } else {
                    "AND 1=0"
                }
        } else {
            """
            CREATE TABLE ${table.toQuoted()} (
            ${joinQuotableValues(colNames, separateBy = ",\n    ")}
            ${
                primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }
                    ?.let { "," + generateSqlSingle(it) }.orEmpty()
            } 
            )
            """.trimIndent()
        }
    }
}

package net.futureset.kontroldb.oracle.template

import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateTemporaryTableTemplate(db: EffectiveSettings) :
    DbAwareTemplate<CreateTable>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<CreateTable> {
        return CreateTable::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun convertSingle(): CreateTable.() -> String? = {
        val colNames =
            fromSelect?.columns?.map { it.columnName } ?: columnDefinitions.takeUnless { it.isEmpty() }.orEmpty()
        """
            ${
            when (table.tablePersistence) {
                TablePersistence.TEMPORARY -> "CREATE GLOBAL TEMPORARY "
                TablePersistence.GLOBAL_TEMPORARY -> "CREATE GLOBAL TEMPORARY "
                TablePersistence.NORMAL -> "CREATE "
            }
        }TABLE ${table.toSql()} (
            ${
            forEach(
                colNames,
                separateBy = ",\n    ",
            )
        }${
            primaryKey?.takeIf { table.tablePersistence == TablePersistence.NORMAL }?.let { "," + otherTemplate(it) }
                .orEmpty()
        } )
                       ${
            "ON COMMIT ${if (preserveRowsOnCommit) "PRESERVE" else "DELETE"} ROWS".takeIf { table.tablePersistence != TablePersistence.NORMAL }
                .orEmpty()
        }
        ${
            fromSelect?.let { selectQuery ->
                " AS (" +
                    template(selectQuery)?.convert(selectQuery)?.first() +
                    (if (selectQuery.predicate?.isEmpty() != false) " WHERE 1=0" else " AND 1=0")
                        .takeUnless { selectQuery.includeData }.orEmpty() + ")"
            }.orEmpty()
        }
        """.trimIndent()
    }
}

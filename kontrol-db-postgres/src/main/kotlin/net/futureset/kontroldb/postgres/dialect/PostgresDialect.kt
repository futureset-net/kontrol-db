package net.futureset.kontroldb.postgres.dialect

import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.StandardColumnTypes
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.AnsiDialect
import net.futureset.kontroldb.settings.DbDialect
import org.koin.core.annotation.Singleton
import java.nio.file.Path
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Properties

@Singleton(binds = [DbDialect::class])
class PostgresDialect : AnsiDialect {

    override val supportsTablespace: Boolean = true
    override val supportsCatalogs: Boolean = false

    override val openQuote = "\""
    override val closeQuote = "\""
    override val batchSeparator = "\n\n"
    override val statementSeparator = ";\n\n"
    override val nullableByDefault = true
    override val ddlInTransactions = true
    override val databaseName = "postgres"
    override val literalTrue: String = "true"
    override val literalFalse: String = "false"
    override val order: Int = 10
    override fun dbNowTimestamp(): String {
        return "CURRENT_TIMESTAMP"
    }

    override fun startTransaction(id: Int): String {
        return "BEGIN;"
    }

    override fun endTransaction(id: Int): String {
        return "COMMIT;"
    }

    override fun connectionProps(): Properties {
        return super.connectionProps().apply {
            put("escapeSyntaxCallMode", "callIfNoReturn")
        }
    }

    override fun literalDate(date: LocalDate): String {
        return super.literalDate(date) + "::date"
    }

    override fun literalDatetime(date: LocalDateTime): String {
        return super.literalDatetime(date) + "::timestamp"
    }

    override fun getNativeType(columnType: ColumnType): String {
        return when (columnType) {
            StandardColumnTypes.LOCALDATETIME -> "timestamp"
            StandardColumnTypes.DATETIME -> "timestamptz"
            StandardColumnTypes.DATE -> "date"
            StandardColumnTypes.LOCALDATE -> "date"
            StandardColumnTypes.BOOLEAN -> "boolean"
            else -> super.getNativeType(columnType)
        }
    }

    override fun tempTable(table: Table): Table {
        return when (table.tablePersistence) {
            TablePersistence.TEMPORARY -> table.copy(schemaObject = SchemaObject(name = DbIdentifier("#" + (table.schemaObject.name.name.trimStart('#')))))
            TablePersistence.GLOBAL_TEMPORARY -> table.copy(schemaObject = SchemaObject(name = DbIdentifier("##" + (table.schemaObject.name.name.trimStart('#')))))
            else -> table
        }
    }

    override fun runScriptAgainstDb(emptyDb: Connection, sqlScript: Path) {
        TODO("Not yet implemented")
    }
}

package net.futureset.kontroldb.settings

import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.Table
import java.nio.file.Path
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

val SQL_TIMESTAMP_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
val SQL_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

interface DbDialect : Comparable<DbDialect> {
    val supportsCatalogs: Boolean
    val supportsTablespace: Boolean
    val openQuote: String
    val closeQuote: String
    val batchSeparator: String
    val statementSeparator: String
    val nullableByDefault: Boolean
    val ddlInTransactions: Boolean
    val databaseName: String
    val literalTrue: String
    val literalFalse: String
    val order: Int

    fun changeScript(
        scriptLines: MutableList<String>,
        targetTool: String,
    ) {
        // Do nothing by default
    }

    fun getNativeType(columnType: ColumnType): String

    fun dbNowTimestamp(): String

    fun literalDate(date: LocalDate): String = "'" + SQL_DATE_FORMAT.format(date) + "'"

    fun connectionProps(): Properties = Properties()

    fun quote(str: String) = openQuote + str + closeQuote

    override fun compareTo(other: DbDialect): Int = compareBy(DbDialect::databaseName).thenBy(DbDialect::order).compare(other, this)

    fun literalDatetime(date: LocalDateTime): String = "'" + SQL_TIMESTAMP_FORMAT.format(date) + "'"

    fun tempTable(table: Table): Table = table

    fun runScriptAgainstDb(
        emptyDb: Connection,
        sqlScript: Path,
    ) {
        TODO("Not yet implemented")
    }

    fun startTransaction(id: Int): String

    fun endTransaction(id: Int): String
}

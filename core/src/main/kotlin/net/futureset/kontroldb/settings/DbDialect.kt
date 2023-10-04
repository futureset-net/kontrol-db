package net.futureset.kontroldb.settings

import net.futureset.kontroldb.ColumnType
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val SQL_TIMESTAMP_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
val SQL_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
interface DbDialect {
    val supportsCatalogs: Boolean
    val supportsTablespace: Boolean
    val openQuote: String
    val closeQuote: String
    val statementSeparator: String
    val nullableByDefault: Boolean
    val ddlInTransactions: Boolean
    val databaseName: String

    fun getNativeType(columnType: ColumnType): String

    fun now(): String

    fun literalDate(date: LocalDate): String {
        return "'" + SQL_DATE_FORMAT.format(date) + "'"
    }
    fun literalDatetime(date: LocalDateTime): String {
        return "'" + SQL_TIMESTAMP_FORMAT.format(date) + "'"
    }

    fun closeHook(): (Connection) -> Unit
}

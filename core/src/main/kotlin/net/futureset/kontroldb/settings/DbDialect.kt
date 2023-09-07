package net.futureset.kontroldb.settings

import net.futureset.kontroldb.DbColumnType
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime

interface DbDialect {

    val supportsCatalogs: Boolean
    val supportsTablespace: Boolean
    val openQuote: String
    val closeQuote: String
    val statementSeparator: String
    val nullableByDefault: Boolean
    val ddlInTransactions: Boolean

    fun getNativeType(dbColumnType: DbColumnType): String

    fun now(): String

    fun literalDate(date: LocalDate): String
    fun literalDatetime(date: LocalDateTime): String

    fun closeHook(): (Connection) -> Unit
}

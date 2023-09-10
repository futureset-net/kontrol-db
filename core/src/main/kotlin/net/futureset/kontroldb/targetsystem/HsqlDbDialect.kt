package net.futureset.kontroldb.targetsystem

import net.futureset.kontroldb.AnsiDialect
import net.futureset.kontroldb.executeSql
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HsqlDbDialect : AnsiDialect {

    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy")

    override val supportsTablespace: Boolean = false
    override val supportsCatalogs: Boolean = true

    override val openQuote = "\""
    override val closeQuote = "\""
    override val statementSeparator = ";"
    override val nullableByDefault = true
    override val ddlInTransactions = false
    override val name = "hsqldb"

    override fun closeHook(): (Connection) -> Unit {
        return { connection -> connection.executeSql("SHUTDOWN") }
    }

    override fun now(): String {
        return "CURRENT_TIMESTAMP"
    }

    override fun literalDatetime(date: LocalDateTime): String {
        return "TO_TIMESTAMP('${date.format(dateTimeFormatter)}','DD/MM/YYYY HH:MI:SS')"
    }

    override fun literalDate(date: LocalDate): String {
        return "TO_DATE('${date.format(dateFormatter)}','DD/MM/YYYY')"
    }
}
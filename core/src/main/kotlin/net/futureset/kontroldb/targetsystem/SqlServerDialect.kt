package net.futureset.kontroldb.targetsystem

import net.futureset.kontroldb.AnsiDialect
import java.nio.file.Path
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SqlServerDialect : AnsiDialect {

    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy")

    override val supportsTablespace: Boolean = true
    override val supportsCatalogs: Boolean = true

    override val openQuote = "["
    override val closeQuote = "]"
    override val batchSeparator = "\ngo\n"
    override val statementSeparator = ";\n\n"
    override val nullableByDefault = true
    override val ddlInTransactions = true
    override val databaseName = "sqlserver"

    override fun closeHook(): (Connection) -> Unit {
        return { }
    }

    override fun now(): String {
        return "CURRENT_TIMESTAMP"
    }

    override fun startTransaction(id: Int): String {
        return "BEGIN TRAN"
    }

    override fun endTransaction(id: Int): String {
        return "COMMIT TRAN"
    }

    override fun literalDatetime(date: LocalDateTime): String {
        return "TO_TIMESTAMP('${date.format(dateTimeFormatter)}','DD/MM/YYYY HH:MI:SS')"
    }

    override fun runScriptAgainstDb(emptyDb: Connection, sqlScript: Path) {
        TODO("Not yet implemented")
    }

    override fun literalDate(date: LocalDate): String {
        return "TO_DATE('${date.format(dateFormatter)}','DD/MM/YYYY')"
    }
}

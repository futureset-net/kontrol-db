package net.futureset.kontroldb.targetsystem

import net.futureset.kontroldb.AnsiDialect
import net.futureset.kontroldb.executeSql
import java.sql.Connection
import java.time.format.DateTimeFormatter

open class HsqlDbDialect : AnsiDialect {

    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

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
}

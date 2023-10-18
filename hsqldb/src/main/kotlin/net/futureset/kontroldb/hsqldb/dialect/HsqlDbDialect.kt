package net.futureset.kontroldb.hsqldb.dialect

import net.futureset.kontroldb.AnsiDialect
import net.futureset.kontroldb.modelchange.executeSql
import net.futureset.kontroldb.settings.DbDialect
import org.hsqldb.cmdline.SqlFile
import org.koin.core.annotation.Singleton
import java.nio.file.Path
import java.sql.Connection

@Singleton(binds = [DbDialect::class])
class HsqlDbDialect : AnsiDialect {

    override val supportsTablespace: Boolean = false
    override val supportsCatalogs: Boolean = true

    override val openQuote = "\""
    override val closeQuote = "\""
    override val batchSeparator = ";\n"
    override val statementSeparator = ";\n"
    override val nullableByDefault = true
    override val ddlInTransactions = true
    override val databaseName = "hsqldb"

    override fun closeHook(): (Connection) -> Unit {
        return { connection -> connection.executeSql("SHUTDOWN") }
    }

    override fun startTransaction(id: Int): String {
        return "START TRANSACTION READ WRITE"
    }

    override fun endTransaction(id: Int): String {
        return "COMMIT"
    }

    override fun now(): String {
        return "CURRENT_TIMESTAMP"
    }

    override fun runScriptAgainstDb(emptyDb: Connection, sqlScript: Path) {
        val sf = SqlFile(sqlScript.toFile())
        sf.connection = emptyDb
        sf.execute()
    }
}

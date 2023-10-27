package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.executeSql
import net.futureset.kontroldb.settings.EffectiveSettings
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class ApplyDirectlyMigrationHandler(val effectiveSettings: EffectiveSettings) : MigrationHandler {

    private val connectionHolder = ConnectionHolder(this::makeConnection)

    private val consoleResultSetHandler: (ResultSet) -> Unit = { rs ->
        val headings = mutableListOf<String>()
        for (i in 1..rs.metaData.columnCount) {
            headings.add(rs.metaData.getColumnLabel(i) ?: rs.metaData.getColumnName(i))
        }
        val results = mutableListOf<Map<String, String>>()
        while (rs.next()) {
            results.add(headings.associateWith(rs::getString))
        }
        println(
            results.dataTable(
                *headings.map { heading -> heading to { m: Map<String, String> -> m[heading] } }
                    .toTypedArray(),
            ) + "\n",
        )
    }

    override fun executeModelChange(change: ModelChange, rawChanges: List<String>) {
        withConnection {
            rawChanges
                .toList()
                .forEach { sql ->
                    it.executeSql(sql, if (change is SupportsResultSetHandler) change.resultSetHandler(effectiveSettings) else consoleResultSetHandler)
                }
        }
    }

    override fun executeRefactoring(refactoring: Refactoring) {
    }

    override fun <T> wrapInTransactionOnWhen(predicate: Boolean, lambda: () -> T): T {
        return if (predicate) {
            connectionHolder.wrapInTransaction(lambda)
        } else {
            lambda()
        }
    }

    fun close() {
        connectionHolder.close()
    }

    private fun makeConnection(): Connection {
        return DriverManager.getConnection(
            effectiveSettings.jdbcUrl,
            effectiveSettings.username,
            effectiveSettings.password,
        )
    }

    override fun end() {
        connectionHolder.close()
    }

    fun <T> withConnection(block: (Connection) -> T): T {
        return connectionHolder.withConnection(block)
    }
}

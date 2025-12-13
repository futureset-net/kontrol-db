package net.futureset.kontroldb.migration

import net.futureset.kontroldb.ConnectionHolder
import net.futureset.kontroldb.SupportsResultSetHandler
import net.futureset.kontroldb.dataTable
import net.futureset.kontroldb.executeSql
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.settings.EffectiveSettings
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class ApplyDirectlyMigrationHandler(
    private val effectiveSettings: EffectiveSettings,
) : MigrationHandler {
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
                *headings
                    .map { heading -> heading to { m: Map<String, String> -> m[heading] } }
                    .toTypedArray(),
            ) + "\n",
        )
    }

    override fun executeModelChange(
        change: ModelChange,
        rawChanges: List<String>,
    ) {
        withConnection {
            rawChanges.forEach { sql ->
                it.executeSql(
                    sql,
                    if (change is SupportsResultSetHandler) change.resultSetHandler(effectiveSettings) else consoleResultSetHandler,
                )
            }
        }
    }

    override fun executeRefactoring(refactoring: Refactoring) {
    }

    override fun <T> wrapInTransactionOnWhen(
        predicate: Boolean,
        lambda: () -> T,
    ): T = if (predicate) {
        connectionHolder.wrapInTransaction(lambda)
    } else {
        lambda()
    }

    fun close() {
        connectionHolder.close()
    }

    private fun makeConnection(): Connection = DriverManager.getConnection(
        effectiveSettings.jdbcUrl,
        effectiveSettings.connectionProps().apply {
            effectiveSettings.username?.let { put("user", it) }
            effectiveSettings.password?.let { put("password", it) }
        },
    )

    override fun end() {
        connectionHolder.close()
    }

    fun <T> withConnection(block: (Connection) -> T): T = connectionHolder.withConnection(block)
}

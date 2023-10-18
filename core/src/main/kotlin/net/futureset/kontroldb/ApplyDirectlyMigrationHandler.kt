package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.executeSql
import net.futureset.kontroldb.settings.EffectiveSettings
import java.sql.Connection
import java.sql.DriverManager

class ApplyDirectlyMigrationHandler(val effectiveSettings: EffectiveSettings) : MigrationHandler {

    private val currentConnection: ThreadLocal<Connection> = ThreadLocal.withInitial(::makeConnection)

    override fun executeModelChange(change: ModelChange, rawChanges: List<String>) {
        withConnection {
            rawChanges
                .toList()
                .forEach { sql ->
                    it.executeSql(sql)
                }
        }
    }

    override fun executeRefactoring(refactoring: Refactoring) {
    }

    override fun <T> wrapInTransactionOnWhen(predicate: Boolean, lambda: () -> T): T {
        val connection = currentConnection.get()
        return try {
            connection.autoCommit = false
            val result = lambda()
            connection.commit()
            result
        } finally {
            connection.autoCommit = true
        }
    }

    fun close() {
        effectiveSettings.closeHook().invoke(currentConnection.get())
        currentConnection.remove()
    }

    private fun makeConnection(): Connection {
        return DriverManager.getConnection(
            effectiveSettings.jdbcUrl,
            effectiveSettings.username,
            effectiveSettings.password,
        )
    }

    override fun end() {
        currentConnection.remove()
    }

    fun <T> withConnection(block: (Connection) -> T): T {
        var success = false
        try {
            val result = block.invoke(currentConnection.get())
            success = true
            return result
        } finally {
            if (!success) {
                currentConnection.get().rollback()
                currentConnection.remove()
            }
        }
    }
}

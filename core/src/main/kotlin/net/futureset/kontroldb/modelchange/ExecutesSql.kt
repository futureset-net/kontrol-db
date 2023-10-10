package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.settings.EffectiveSettings
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

val sqlLogger: Logger = LoggerFactory.getLogger("SQL")
fun Connection.executeSql(sql: String) {
    var success = false
    try {
        createStatement().use {
            sqlLogger.info("\n$sql")
            if (it.execute(sql)) {
                sqlLogger.info("Affected ${it.updateCount} rows")
            }
        }
        success = true
    } finally {
        if (!success) {
            sqlLogger.error("Failed\n$sql")
        }
    }
}

inline fun <reified T> Connection.executeQuery(sql: String, block: (ResultSet) -> T): List<T> {
    return createStatement().use {
        sqlLogger.info(sql)
        var success = false
        try {
            it.executeQuery(sql)?.use { rs ->
                val results = mutableListOf<T>()
                while (rs.next()) {
                    results.add(block(rs))
                }
                sqlLogger.info("returned ${results.size} rows")
                success = true
                results.toList()
            } ?: emptyList<T>()
        } finally {
            if (!success) {
                sqlLogger.error("Failed\n$sql")
            }
        }
    }
}

interface ExecutesSql {

    val effectiveSettings: EffectiveSettings

    fun <T> withConnection(block: (Connection) -> T): T {
        return DriverManager.getConnection(effectiveSettings.jdbcUrl, effectiveSettings.username, effectiveSettings.password).use {
            block(it)
        }
    }

    fun close() {
        withConnection {
            effectiveSettings.closeHook()(it)
        }
    }
}

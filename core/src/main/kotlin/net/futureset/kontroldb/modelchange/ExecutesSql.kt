package net.futureset.kontroldb.modelchange

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.ResultSet

val sqlLogger: Logger = LoggerFactory.getLogger("SQL")
fun Connection.executeSql(sql: String, resultSetHandler: ((ResultSet) -> Unit)? = null) {
    var success = false
    try {
        createStatement().use { stmt ->
            if (stmt.execute(sql)) {
                sqlLogger.info("connection <$this> \n$sql")
                if (stmt.updateCount >= 0) {
                    sqlLogger.info("connection <$this> Affected ${stmt.updateCount} rows")
                }
            } else {
                sqlLogger.info("connection <$this> \n$sql")
            }
            if (resultSetHandler != null) {
                do {
                    stmt.resultSet?.use { rs ->
                        resultSetHandler.invoke(rs)
                    }
                } while (stmt.moreResults)
            }
        }
        success = true
    } finally {
        if (!success) {
            sqlLogger.error("connection <$this> Failed\n$sql")
        }
    }
}

inline fun <reified T> Connection.executeQuery(sql: String, block: (ResultSet) -> T): List<T> {
    return createStatement().use {
        sqlLogger.info("connection <$this> $sql")
        var success = false
        try {
            it.executeQuery(sql)?.use { rs ->
                val results = mutableListOf<T>()
                while (rs.next()) {
                    results.add(block(rs))
                }
                sqlLogger.info("connection <$this> returned ${results.size} rows")
                success = true
                results.toList()
            } ?: emptyList<T>()
        } finally {
            if (!success) {
                sqlLogger.error("connection <$this> Failed\n$sql")
            }
        }
    }
}

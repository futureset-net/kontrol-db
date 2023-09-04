package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.SortedSet

val sqlLogger = LoggerFactory.getLogger("SQL")
fun Connection.executeSql(sql: String) {
    createStatement().use {
        sqlLogger.info(sql)
        if (it.execute(sql)) {
            sqlLogger.info("Affected ${it.updateCount} rows")
        }
    }
}

inline fun <reified T> Connection.executeQuery(sql: String, block: (ResultSet) -> T): List<T> {
    return createStatement().use {
        sqlLogger.info(sql)
        it.executeQuery(sql)?.use { rs ->
            val results = mutableListOf<T>()
            while (rs.next()) {
                results.add(block(rs))
            }
            sqlLogger.info("returned ${results.size} rows")
            results.toList()
        } ?: emptyList()
    }
}

class SqlExecutor(private val effectiveSettings: EffectiveSettings) {

    fun executeAll(sqlToApply: List<String>) {
        withConnection { conn ->
            sqlToApply.forEach(conn::executeSql)
        }
    }

    fun <T> withConnection(block: (Connection) -> T): T {
        return DriverManager.getConnection(effectiveSettings.jdbcUrl).use {
            block(it)
        }
    }

    fun close() {
        withConnection {
            effectiveSettings.closeHook()(it)
        }
    }

    fun getCurrentState(): SortedSet<AppliedRefactoring> {
        try {
            return withConnection {
                it.executeQuery<AppliedRefactoring>(
                    "SELECT " +
                        "EXECUTION_ORDER,ID,CHECK_SUM,EXECUTION_SEQUENCE".split(",").map(::DbIdentifier)
                            .joinToString { d -> d.toSql(effectiveSettings) } + " FROM " + effectiveSettings.versionControlTable.toSql(effectiveSettings),
                ) { rs ->
                    AppliedRefactoring(
                        executionOrder = ExecutionOrder.fromStringValue(rs.getString(1)),
                        id = rs.getString(2),
                        checksum = rs.getString(3),
                        executionSequence = rs.getInt(4),
                    )
                }.toSortedSet(Comparator.comparing(AppliedRefactoring::executionOrder)::compare)
            }
        } catch (e: SQLException) {
            sqlLogger.warn("Cannot retrieve current state", e)
            return sortedSetOf()
        }
    }
}

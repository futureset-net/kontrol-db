package net.futureset.kontroldb

import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.SQLException

class ConnectionHolder(private val connectionSupplier: () -> Connection) : AutoCloseable {

    private var connection: Connection? = null
    private var autoCommit: Boolean = true
    private val logger = LoggerFactory.getLogger(ConnectionHolder::class.java)
    private var transactionDepth = 0
    private var connectionId = 1

    fun <T> withConnection(block: (Connection) -> T): T {
        var success = false
        try {
            val result = block.invoke(ProtectedConnection(cachedConnection(), connectionId) { autoCommit })
            success = true
            return result
        } finally {
            if (autoCommit) {
                cleanUp(success)
            }
        }
    }

    private fun startTransaction() {
        if (autoCommit) {
            cachedConnection().autoCommit = false
            cachedConnection()
            autoCommit = false
            logger.info("START TRANSACTION")
        }
        transactionDepth++
    }

    private fun endTransaction(success: Boolean) {
        if (--transactionDepth == 0) {
            val currentConnection = connection
            if (currentConnection != null) {
                if (success) {
                    logger.info("COMMIT TRANSACTION")
                    currentConnection.commit()
                    currentConnection.autoCommit = false
                } else {
                    logger.info("ROLLBACK TRANSACTION")
                    ignorePossibleSqlError(currentConnection::rollback)
                    close()
                }
            }
        } else if (transactionDepth < 0) {
            throw IllegalStateException("endTransaction without startTransaction")
        }
    }

    private fun ignorePossibleSqlError(lambda: () -> Unit) {
        try {
            lambda()
        } catch (e: SQLException) {
            logger.debug("Ignoring SQL exception", e)
        }
    }

    private fun cleanUp(wasSuccessful: Boolean) {
        if (wasSuccessful) {
            connection?.autoCommit = true
            autoCommit = true
        } else {
            close()
        }
    }

    fun <T> wrapInTransaction(lambda: () -> T): T {
        var success = false
        return try {
            startTransaction()
            val result = lambda()
            success = true
            result
        } finally {
            endTransaction(success)
            cleanUp(success)
        }
    }

    private fun cachedConnection(): Connection {
        val currentConnection = connection ?: connectionSupplier().also {
            autoCommit = true
            connectionId++
        }
        connection = currentConnection
        return currentConnection
    }

    override fun close() {
        val currentConnection = connection
        if (currentConnection != null) {
            currentConnection.close()
            connection = null
            autoCommit = true
        }
    }

    class ProtectedConnection(
        realConnection: Connection,
        private val connectionId: Int,
        private val currentAutoCommitState: () -> Boolean,
    ) :
        Connection by realConnection {
        private val logger = LoggerFactory.getLogger(ConnectionHolder::class.java)

        override fun setAutoCommit(autoCommit: Boolean) {
            if (autoCommit != currentAutoCommitState()) {
                logger.warn("Attempt to change autocommit state to $autoCommit failed")
            }
        }

        override fun toString(): String {
            return "connectionId = $connectionId"
        }

        override fun commit() {
            if (!autoCommit) {
                logger.warn("Attempt to commit transaction ignored. use ConnectionHolder.withTransaction")
            }
        }

        override fun close() {
            logger.warn("Ignore connection.close")
        }
    }
}

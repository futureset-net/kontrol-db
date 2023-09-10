package net.futureset.kontroldb.settings

import net.futureset.kontroldb.DbColumnType
import net.futureset.kontroldb.DbIdentifier
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime

class EffectiveSettings(
    private val dbDialect: DbDialect,
    private val executionSettings: ExecutionSettings,
    private val targetSettings: TargetSettings,
) {

    val dbName = dbDialect.name
    val outputTablespace = (dbDialect.supportsTablespace && executionSettings.outputTablespace)
    val outputSchema = executionSettings.outputSchema
    val outputCatalog = executionSettings.outputCatalog && dbDialect.supportsCatalogs
    val defaultSchema = targetSettings.defaultSchema?.let(::DbIdentifier)
    val defaultCatalog =
        targetSettings.defaultCatalog?.takeIf { executionSettings.outputCatalog && dbDialect.supportsCatalogs && executionSettings.outputSchema }?.let(::DbIdentifier)
    val versionControlTable = targetSettings.versionControlTable
    val defaultTablespace = targetSettings.defaultTablespace?.takeIf { outputTablespace }?.let(::DbIdentifier)
    val defaultIndexTablespace = (targetSettings.defaultIndexTablespace ?: targetSettings.defaultTablespace)?.takeIf { outputTablespace }?.let(::DbIdentifier)
    val jdbcUrl = targetSettings.jdbcUrl
    val username = targetSettings.username
    val password = targetSettings.password
    val supportsCatalogs = dbDialect.supportsCatalogs
    val supportsTablespace = dbDialect.supportsTablespace
    val openQuote = dbDialect.openQuote
    val closeQuote = dbDialect.closeQuote
    val statementSeparator = dbDialect.statementSeparator
    val nullableByDefault = dbDialect.nullableByDefault
    val ddlInTransactions = dbDialect.ddlInTransactions
    var isScripting = false
    fun getNativeType(dbColumnType: DbColumnType): String = dbDialect.getNativeType(dbColumnType)

    fun now() = dbDialect.now()

    fun literalDate(date: LocalDate) = dbDialect.literalDate(date)

    fun literalDatetime(date: LocalDateTime) = dbDialect.literalDatetime(date)
    fun closeHook(): (Connection) -> Unit = dbDialect.closeHook()
}

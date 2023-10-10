package net.futureset.kontroldb.settings

import net.futureset.kontroldb.KontrolDbState
import net.futureset.kontroldb.TemplateResolver

class EffectiveSettings(
    private val dbDialect: DbDialect,
    private val executionSettings: IExecutionSettings,
    private val targetSettings: ITargetSettings,
    val migrationRunId: Long,
) : DbDialect by dbDialect, ITargetSettings by targetSettings, IExecutionSettings by executionSettings {

    lateinit var templateResolver: TemplateResolver
    var startState: KontrolDbState? = null
    override val isOutputTablespace = (dbDialect.supportsTablespace && executionSettings.isOutputTablespace)
    override val isOutputCatalog = executionSettings.isOutputCatalog && dbDialect.supportsCatalogs
    override val defaultCatalog =
        targetSettings.defaultCatalog?.takeIf { executionSettings.isOutputCatalog && dbDialect.supportsCatalogs && executionSettings.isOutputSchema }
    override val defaultTablespace = targetSettings.defaultTablespace?.takeIf { isOutputTablespace }
    override val defaultIndexTablespace =
        (targetSettings.defaultIndexTablespace ?: targetSettings.defaultTablespace)?.takeIf { isOutputTablespace }
    var isScripting = false
    override val transactionScope: TransactionScope = if (dbDialect.ddlInTransactions) executionSettings.transactionScope else TransactionScope.STATEMENT
}

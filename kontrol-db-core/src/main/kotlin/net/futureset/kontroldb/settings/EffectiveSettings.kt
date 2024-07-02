package net.futureset.kontroldb.settings

import net.futureset.kontroldb.KontrolDbState
import net.futureset.kontroldb.ResourceResolver
import net.futureset.kontroldb.generator.SqlGeneratorFactory

class EffectiveSettings(
    private val dbDialect: DbDialect,
    private val executionSettings: IExecutionSettings,
    private val targetSettings: ITargetSettings,
    val sqlGeneratorFactory: SqlGeneratorFactory,
) : DbDialect by dbDialect, ITargetSettings by targetSettings, IExecutionSettings by executionSettings {

    val migrationRunId: Long = System.currentTimeMillis()
    val resourceResolver: ResourceResolver = ResourceResolver(externalFileRoot)
    var startState: KontrolDbState? = null
    override val isOutputTablespace = (dbDialect.supportsTablespace && executionSettings.isOutputTablespace)
    override val isOutputCatalog = executionSettings.isOutputCatalog && dbDialect.supportsCatalogs
    override val defaultCatalog =
        targetSettings.defaultCatalog?.takeIf { executionSettings.isOutputCatalog && dbDialect.supportsCatalogs && executionSettings.isOutputSchema }
    override val defaultTablespace = targetSettings.defaultTablespace?.takeIf { isOutputTablespace }
    override val defaultIndexTablespace =
        (targetSettings.defaultIndexTablespace ?: targetSettings.defaultTablespace)?.takeIf { isOutputTablespace }
    override val transactionScope: TransactionScope =
        if (dbDialect.ddlInTransactions) executionSettings.transactionScope else TransactionScope.STATEMENT
}

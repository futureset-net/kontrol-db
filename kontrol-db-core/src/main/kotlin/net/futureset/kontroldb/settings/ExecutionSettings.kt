package net.futureset.kontroldb.settings

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.OperatingSystem
import java.nio.file.Path
import java.nio.file.Paths

enum class TransactionScope {
    STATEMENT,
    REFACTORING,
    MIGRATION,
}

interface IExecutionSettings {
    val isOutputSchema: Boolean
    val isOutputCatalog: Boolean
    val isOutputTablespace: Boolean
    val operatingSystem: OperatingSystem
    val outputDirectory: Path
    val externalFileRoot: Path
    val transactionScope: TransactionScope
}

data class ExecutionSettings(

    override val isOutputSchema: Boolean = false,
    override val isOutputCatalog: Boolean = false,
    override val isOutputTablespace: Boolean = false,
    override val operatingSystem: OperatingSystem = OperatingSystem.WINDOWS,
    override val outputDirectory: Path = Paths.get(""),
    override val externalFileRoot: Path = Paths.get(""),
    override val transactionScope: TransactionScope = TransactionScope.REFACTORING,
) : IExecutionSettings

class ExecutionSettingsBuilder(private var executionSettings: ExecutionSettings = ExecutionSettings()) : Builder<ExecutionSettingsBuilder, ExecutionSettings> {

    fun isOutputSchema(isOutputSchema: Boolean) =
        apply { executionSettings = executionSettings.copy(isOutputSchema = isOutputSchema) }

    fun isOutputCatalog(isOutputCatalog: Boolean) =
        apply { executionSettings = executionSettings.copy(isOutputCatalog = isOutputCatalog) }

    fun isOutputTablespace(isOutputTablespace: Boolean) =
        apply { executionSettings = executionSettings.copy(isOutputTablespace = isOutputTablespace) }

    fun externalFileRoot(path: Path) = apply {
        executionSettings = executionSettings.copy(externalFileRoot = path)
    }

    fun transactionScope(transactionScope: TransactionScope) = apply {
        executionSettings = executionSettings.copy(transactionScope = transactionScope)
    }

    fun outputDirectory(outputDirectory: Path) =
        apply { executionSettings = executionSettings.copy(outputDirectory = outputDirectory) }

    override fun build(): ExecutionSettings = executionSettings
}

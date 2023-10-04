package net.futureset.kontroldb.settings

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.OperatingSystem
import java.nio.file.Path
import java.nio.file.Paths

interface IExecutionSettings {
    val isOutputSchema: Boolean
    val isOutputCatalog: Boolean
    val isOutputTablespace: Boolean
    val operatingSystem: OperatingSystem
    val outputDirectory: Path
}

data class ExecutionSettings(

    override val isOutputSchema: Boolean = false,
    override val isOutputCatalog: Boolean = false,
    override val isOutputTablespace: Boolean = false,
    override val operatingSystem: OperatingSystem = OperatingSystem.WINDOWS,
    override val outputDirectory: Path = Paths.get(""),
) : IExecutionSettings

class ExecutionSettingsBuilder : Builder<ExecutionSettingsBuilder, ExecutionSettings> {

    private var isOutputSchema: Boolean = false
    private var isOutputCatalog: Boolean = false
    private var isOutputTablespace: Boolean = false
    private var operatingSystem: OperatingSystem = OperatingSystem.WINDOWS
    private var outputDirectory: Path = Paths.get("")

    fun isOutputSchema(isOutputSchema: Boolean) = apply { this.isOutputSchema = isOutputSchema }
    fun isOutputCatalog(isOutputCatalog: Boolean) = apply { this.isOutputCatalog = isOutputCatalog }
    fun isOutputTablespace(isOutputTablespace: Boolean) = apply { this.isOutputTablespace = isOutputTablespace }
    fun outputDirectory(outputDirectory: Path) = apply { this.outputDirectory = outputDirectory }
    override fun build(): ExecutionSettings =
        ExecutionSettings(isOutputSchema, isOutputCatalog, isOutputTablespace, operatingSystem, outputDirectory)
}

package net.futureset.kontroldb.settings

import net.futureset.kontroldb.OperatingSystem
import java.nio.file.Path
import java.nio.file.Paths

data class ExecutionSettings(

    var outputSchema: Boolean = false,
    var outputCatalog: Boolean = false,
    var outputTablespace: Boolean = false,
    var operatingSystem: OperatingSystem = OperatingSystem.WINDOWS,
    val outputDirectory: Path = Paths.get(""),
)

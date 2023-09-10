package net.futureset.kontroldb.settings

data class ExecutionSettings(

    var outputSchema: Boolean = false,
    var outputCatalog: Boolean = false,
    var outputTablespace: Boolean = false,
)

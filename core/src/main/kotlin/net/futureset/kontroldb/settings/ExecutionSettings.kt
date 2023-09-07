package net.futureset.kontroldb.settings

data class ExecutionSettings(

    val outputSchema: Boolean = false,
    val outputCatalog: Boolean = false,
    val outputTablespace: Boolean = false,
)

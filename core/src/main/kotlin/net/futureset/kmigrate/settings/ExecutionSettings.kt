package net.futureset.kmigrate.settings

data class ExecutionSettings(

    val outputSchema: Boolean = false,
    val outputCatalog: Boolean = false,
    val outputTablespace: Boolean = false,
)

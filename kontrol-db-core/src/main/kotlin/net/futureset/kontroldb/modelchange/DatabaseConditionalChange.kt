package net.futureset.kontroldb.modelchange

data class DatabaseConditionalChange(
    val dbPredicate: (String) -> Boolean,
    val wrappedChange: ModelChange,
) : ModelChange

package net.futureset.kontroldb

data class AppliedRefactoring(
    val executionOrder: ExecutionOrder,
    val id: String,
    val checksum: String,
    val executionSequence: Int,
)

package net.futureset.kmigrate

data class AppliedRefactoring(
    val executionOrder: ExecutionOrder,
    val id: String,
    val checksum: String,
    val executionSequence: Int,
)

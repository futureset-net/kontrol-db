package net.futureset.kontroldb

data class AppliedRefactoring(
    val executionOrder: ExecutionOrder,
    val id: String,
    val checksum: String,
    val executionSequence: Int,
) : Comparable<AppliedRefactoring> {
    override fun compareTo(other: AppliedRefactoring): Int =
        Comparator.comparing(AppliedRefactoring::executionOrder)
            .thenComparing(AppliedRefactoring::id)
            .compare(this, other)
}

package net.futureset.kontroldb.refactoring

data class AppliedRefactoring(
    val executionOrder: ExecutionOrder,
    val id: String,
    val checksum: String,
    val executionSequence: Int,
    val rolledback: Boolean,
) : Comparable<AppliedRefactoring> {
    override fun compareTo(other: AppliedRefactoring): Int = Comparator
        .comparing(AppliedRefactoring::executionOrder)
        .thenComparing(AppliedRefactoring::id)
        .compare(this, other)
}

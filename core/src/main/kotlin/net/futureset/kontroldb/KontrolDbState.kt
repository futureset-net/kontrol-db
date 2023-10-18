package net.futureset.kontroldb

data class KontrolDbState(
    val refactoringsInSourceControl: List<Refactoring>,
    val refactoringsToApply: List<Refactoring>,
    val lastExecutionSequence: Int,
    val appliedRefactorings: Map<String, AppliedRefactoring>,
) {

    override fun toString(): String {
        return refactoringsInSourceControl.dataTable(
            "SOURCE_CONTROL" to Refactoring::id,
            "AUTHOR" to { it.executionOrder.author },
            "WILL_APPLY" to { if (it in refactoringsToApply) "Y" else "" },
            "ALREADY_APPLIED" to { if (appliedRefactorings.containsKey(it.id())) "Y" else "" },
        )
    }
}

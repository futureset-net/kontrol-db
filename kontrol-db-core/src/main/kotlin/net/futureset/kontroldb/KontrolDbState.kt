package net.futureset.kontroldb

import net.futureset.kontroldb.refactoring.AppliedRefactoring
import net.futureset.kontroldb.refactoring.Refactoring

data class KontrolDbState(
    val refactoringsInSourceControl: List<Refactoring>,
    val refactoringsToApply: List<Refactoring>,
    val lastExecutionSequence: Int,
    val appliedRefactorings: Map<String, AppliedRefactoring>,
) {
    override fun toString(): String = refactoringsInSourceControl.dataTable(
        "AUTHOR" to { it.executionOrder.author },
        "WILL_APPLY" to { if (it in refactoringsToApply) "Y" else "" },
        "ALREADY_APPLIED" to { if (appliedRefactorings.containsKey(it.id())) "Y" else "" },
        "SOURCE_CONTROL" to Refactoring::id,
    )
}

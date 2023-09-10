package net.futureset.kontroldb

data class KontrolDbState(
    val allSourceControlledChanges: List<Refactoring>,
    val changesToApply: List<Refactoring>,
    val lastExecutionSequence: Int,
    val appliedChanges: Map<String, AppliedRefactoring>,
)

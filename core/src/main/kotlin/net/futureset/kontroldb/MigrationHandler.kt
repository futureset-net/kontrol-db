package net.futureset.kontroldb

interface MigrationHandler {

    fun start() {
    }
    fun executeModelChange(change: ModelChange, rawChanges: List<String>)
    fun executeRefactoring(refactoring: Refactoring)

    fun end() {
    }

    fun <T> wrapInTransactionOnWhen(predicate: Boolean, lambda: () -> T): T
}

package net.futureset.kontroldb.migration

import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.refactoring.Refactoring

interface MigrationHandler {
    fun start() {
    }

    fun executeModelChange(
        change: ModelChange,
        rawChanges: List<String>,
    )

    fun executeRefactoring(refactoring: Refactoring)

    fun end() {
    }

    fun <T> wrapInTransactionOnWhen(
        predicate: Boolean,
        lambda: () -> T,
    ): T
}

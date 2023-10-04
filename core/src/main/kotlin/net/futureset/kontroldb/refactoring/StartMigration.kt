package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.ExecuteMode
import net.futureset.kontroldb.Refactoring

class StartMigration : Refactoring(
    executionOrder = VERSION_TABLE_ID.copy(seq = 2),
    forward = emptyList(),
    rollback = emptyList(),
    executeMode = ExecuteMode.ALWAYS,
)

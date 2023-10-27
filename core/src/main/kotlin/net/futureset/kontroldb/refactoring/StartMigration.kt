package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.EARLIEST_CHANGE
import net.futureset.kontroldb.ExecuteMode
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.modelchange.StartBanner

class StartMigration :
    Refactoring(
        executionOrder = EARLIEST_CHANGE.justAfter(),
        forward = listOf(StartBanner("Start Migration")),
        rollback = emptyList(),
        executeMode = ExecuteMode.ALWAYS,
    ),
    AStartEndMarker

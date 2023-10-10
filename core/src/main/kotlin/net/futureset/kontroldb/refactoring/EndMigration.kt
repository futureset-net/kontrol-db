package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.ExecuteMode
import net.futureset.kontroldb.LATEST_CHANGE
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.modelchange.EndBanner

class EndMigration :
    Refactoring(
        executionOrder = LATEST_CHANGE,
        forward = listOf(EndBanner("End Migration")),
        rollback = emptyList(),
        executeMode = ExecuteMode.ALWAYS,
    ),
    AStartEndMarker

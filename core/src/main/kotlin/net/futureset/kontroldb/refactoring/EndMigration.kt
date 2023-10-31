package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.modelchange.EndBanner

class EndMigration :
    Refactoring(
        executionOrder = LATEST_CHANGE,
        forward = listOf(EndBanner("End Migration")),
        rollback = emptyList(),
        executeMode = ExecuteMode.ALWAYS,
    ),
    AStartEndMarker

package net.futureset.kontroldb.samples

import net.futureset.kontroldb.modelchange.createRole
import net.futureset.kontroldb.modelchange.dropRole
import net.futureset.kontroldb.refactoring.Refactoring

class CreateARole : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        createRole("FRED")
    },
    rollback = changes {
        dropRole("FRED")
    },
)

package net.futureset.kontroldb.samples

import net.futureset.kontroldb.modelchange.dropIndexIfExists
import net.futureset.kontroldb.modelchange.dropTableIfExists
import net.futureset.kontroldb.refactoring.Refactoring

class DropSomeObjects : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        dropIndexIfExists("IX_LASTNAME") {
            table("CUSTOMER")
        }
        dropTableIfExists("CUSTOMER")
        dropTableIfExists("NON_EXISTENT")
    },
    rollback = emptyList(),
)

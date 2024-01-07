package net.futureset.kontroldb.samples

import net.futureset.kontroldb.modelchange.createSequence
import net.futureset.kontroldb.modelchange.dropSequenceIfExists
import net.futureset.kontroldb.refactoring.Refactoring

class CreateASequence : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        createSequence("MY_SEQUENCE") {
            cache(10)
            cycle()
            incrementBy(2)
            startWith(3)
        }
    },
    rollback = changes {
        dropSequenceIfExists("MY_SEQUENCE")
    },
)

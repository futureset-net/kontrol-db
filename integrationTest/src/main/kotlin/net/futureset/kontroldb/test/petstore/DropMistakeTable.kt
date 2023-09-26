package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.modelchange.dropTable
import org.koin.core.annotation.Single

@Single
class DropMistakeTable : Refactoring(

    executionOrder = executionOrder {
        ymd(2023, 9, 7)
        author("ben")
        sequence(2)
    },
    forward = changes {
        dropTable {
            table("MISTAKE")
        }
    },
    rollback = emptyList(),
)

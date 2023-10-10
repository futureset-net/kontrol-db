package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import org.koin.core.annotation.Single

@Single
class DropMistakeTable : Refactoring(

    executionOrder {
        ymd(2023, 9, 7)
        author("ben")
        sequence(2)
    },
    forward = changes {
        dropTable {
            table("MISTAKE")
        }
    },
    rollback = changes {
        createTable {
            table("MISTAKE")
            column("ID", INT_32)
            tablespace("ANOTHER_TS")
        }
    },
)

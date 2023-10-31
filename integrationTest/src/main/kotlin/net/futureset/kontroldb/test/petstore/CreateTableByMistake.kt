package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.annotation.Single

@Single
class CreateTableByMistake : Refactoring(

    executionOrder {
        ymd(2023, 9, 7)
        author("ben")
        sequence(1)
    },
    forward = changes {
        createTable {
            table("MISTAKE")
            column("ID", INT_32)
            tablespace("ANOTHER_TS")
        }
    },
    rollback = changes {
        dropTable {
            table("MISTAKE")
        }
    },
)

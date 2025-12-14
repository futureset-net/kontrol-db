package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.dsl.createTable
import net.futureset.kontroldb.dsl.dropTable
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.annotation.Single

@Single
class DropMistakeTable :
    Refactoring(
        executionOrder {
            ymd(2023, 9, 7)
            author("ben")
            sequence(2)
        },
        forward =
        changes {
            dropTable("MISTAKE")
        },
        rollback =
        changes {
            createTable("MISTAKE") {
                column("ID", INT32)
                tablespace("ANOTHER_TS")
            }
        },
    )

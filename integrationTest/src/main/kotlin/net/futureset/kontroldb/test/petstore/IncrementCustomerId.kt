package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.ExecuteMode
import net.futureset.kontroldb.KontrolDbDsl.Companion.changes
import net.futureset.kontroldb.KontrolDbDsl.Companion.executionOrder
import net.futureset.kontroldb.Refactoring
import org.koin.core.annotation.Single

@Single
class IncrementCustomerId : Refactoring(
    executionOrder = executionOrder {
        author("ben")
        ymd(2023, 8, 28)
        sequence(2)
    },
    executeMode = ExecuteMode.ON_CHANGE,
    forward = changes {
        updateRow {
            table { name("CUSTOMER") }
            setValueFunction("CUST_ID", "CUST_ID+1")
            whereValue("FIRSTNAME", "ben")
        }
    },
    rollback = changes {
        updateRow {
            table { name("CUSTOMER") }
            setValueFunction("CUST_ID", "CUST_ID-1")
            whereValue("FIRSTNAME", "ben")
        }
    },
)

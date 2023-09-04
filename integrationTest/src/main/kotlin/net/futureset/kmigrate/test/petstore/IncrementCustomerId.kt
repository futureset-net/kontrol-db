package net.futureset.kmigrate.test.petstore

import net.futureset.kmigrate.ExecuteMode
import net.futureset.kmigrate.KMigrateDsl.Companion.changes
import net.futureset.kmigrate.KMigrateDsl.Companion.executionOrder
import net.futureset.kmigrate.Refactoring
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
    rollbacks = changes {
        updateRow {
            table { name("CUSTOMER") }
            setValueFunction("CUST_ID", "CUST_ID-1")
            whereValue("FIRSTNAME", "ben")
        }
    },
)

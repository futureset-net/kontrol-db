package net.futureset.kmigrate.test.petstore

import net.futureset.kmigrate.KMigrateDsl.Companion.changes
import net.futureset.kmigrate.KMigrateDsl.Companion.executionOrder
import net.futureset.kmigrate.Refactoring
import org.koin.core.annotation.Single

@Single
class InsertACustomer : Refactoring(
    executionOrder = executionOrder {
        author("ben")
        ymd(2023, 8, 28)
    },
    forward = changes {
        insertRow {
            table { name("CUSTOMER") }
            value("CUST_ID", 1)
            value("FIRSTNAME", "Ben")
            value("LASTNAME", "Riley")
            value("ADDRESS", "1 Letsbe Avenue")
            value("CITY", "London")
            value("STATE", "NY")
            value("ZIP", "10339")
        }
    },
    rollbacks = changes {
    },
)

package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.modelchange.insert
import org.koin.core.annotation.Single

@Single
class InsertACustomer : Refactoring(
    executionOrder {
        author("ben")
        ymd(2023, 8, 28)
    },
    forward = changes {
        insert {
            table("CUSTOMER")
            values {
                value("CUST_ID", 1)
                value("FIRSTNAME", "Ben")
                value("LASTNAME", "Riley")
                value("ADDRESS", "1 Letsbe Avenue")
                value("CITY", "London")
                value("STATE", "NY")
                value("ZIP", "10339")
            }
        }
    },
    rollback = changes {
    },
)

package net.futureset.kmigrate.test.petstore

import net.futureset.kmigrate.KMigrateDsl.Companion.changes
import net.futureset.kmigrate.KMigrateDsl.Companion.executionOrder
import net.futureset.kmigrate.Refactoring
import net.futureset.kmigrate.StandardColumnTypes.INT_32
import net.futureset.kmigrate.StandardColumnTypes.Varchar
import org.koin.core.annotation.Single

@Single
class CreateCustomerTable : Refactoring(
    executionOrder = executionOrder {
        ymd(2023, 8, 27)
        author("ben")
    },
    forward = changes {
        createTable {
            table { name("CUSTOMER") }
            column("CUST_ID", INT_32)
            column("FIRSTNAME", Varchar(20))
            column("LASTNAME", Varchar(25))
            column("ADDRESS", Varchar(32))
            column("CITY", Varchar(20))
            column("STATE", Varchar(2))
            column("ZIP", Varchar(9))
        }
    },
    rollbacks = changes {
        dropTable {
            table { name("CUSTOMER") }
        }
    },
)

package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.KontrolDbDsl.Companion.changes
import net.futureset.kontroldb.KontrolDbDsl.Companion.executionOrder
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.StandardColumnTypes.Varchar
import org.koin.core.annotation.Single

@Single
class CreateCustomerTable : Refactoring(
    executionOrder = executionOrder {
        ymd(2023, 8, 27)
        author("ben")
    },
    forward = changes {
        createTable {
            table("CUSTOMER")
            column("CUST_ID", INT_32)
            column("FIRSTNAME", Varchar(20))
            column("LASTNAME", Varchar(25))
            column("ADDRESS", Varchar(32))
            column("CITY", Varchar(20))
            column("STATE", Varchar(2))
            column("ZIP", Varchar(9))
        }
        addPrimaryKey {
            table {
                name("CUSTOMER")
            }
            column("CUST_ID")
            constraintName("CUSTOMER_PK")
        }
        addNotNull {
            table {
                name("CUSTOMER")
            }
            column("LASTNAME", Varchar(25))
        }
    },
    rollback = changes {
        dropTable {
            table("CUSTOMER")
        }
    },
)

package net.futureset.kontroldb.samples

import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring

class CreateATable : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        createTable("MY_TABLE") {
            asLocalTemporaryTable() // make temporary
            column("CUST_ID", INT32)
            column("FIRSTNAME", Varchar(20))
            column("LASTNAME", Varchar(25))
            column("ADDRESS", Varchar(32))
            column("CITY", Varchar(20))
            column("STATE", Varchar(2))
            column("ZIP", Varchar(9))
            primaryKey("CUSTOMER_PK") {
                column("CUST_ID")
            }
        }
    },
    rollback = changes {
        dropTable("MY_TABLE")
    },
)

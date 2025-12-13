package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.modelchange.deleteRowsFrom
import net.futureset.kontroldb.modelchange.insertRowsInto
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.annotation.Single

@Single
class InsertACustomer :
    Refactoring(
        executionOrder {
            author("ben")
            ymd(2023, 8, 28)
        },
        forward =
        changes {

            insertRowsInto("CUSTOMER") {
                row {
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
        rollback =
        changes {
            deleteRowsFrom("CUSTOMER") {
                where {
                    col("CUST_ID") eq 1
                }
            }
        },
    )

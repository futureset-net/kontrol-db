package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.ColumnValue
import net.futureset.kontroldb.ColumnValue.Companion.expression
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ExecuteMode
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.modelchange.update
import org.koin.core.annotation.Single

@Single
class IncrementCustomerId : Refactoring(
    executionOrder {
        author("ben")
        ymd(2023, 8, 28)
        sequence(2)
    },
    executeMode = ExecuteMode.ON_CHANGE,
    forward = changes {
        update {
            table("CUSTOMER")
            set("CUST_ID" to expression("CUST_ID+1"))
            where {
                DbIdentifier("FIRSTNAME") eq ColumnValue.valueFromString("ben")
            }
        }
    },
    rollback = changes {
        update {
            table("CUSTOMER")
            set("CUST_ID" to expression("CUST_ID-1"))
            where {
                DbIdentifier("FIRSTNAME") eq ColumnValue.valueFromString("ben")
            }
        }
    },
)

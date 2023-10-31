package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.model.ColumnValue.Companion.expression
import net.futureset.kontroldb.model.ColumnValue.Companion.value
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.modelchange.updateRows
import net.futureset.kontroldb.refactoring.ExecuteMode
import net.futureset.kontroldb.refactoring.Refactoring
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
        updateRows {
            table("CUSTOMER")
            set("CUST_ID" to expression("CUST_ID+1"))
            where {
                DbIdentifier("FIRSTNAME") eq value("ben")
            }
        }
    },
    rollback = changes {
        updateRows {
            table("CUSTOMER")
            set("CUST_ID" to expression("CUST_ID-1"))
            where {
                DbIdentifier("FIRSTNAME") eq value("ben")
            }
        }
    },
)

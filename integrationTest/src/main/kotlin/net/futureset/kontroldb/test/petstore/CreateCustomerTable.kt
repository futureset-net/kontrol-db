package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.addNotNull
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.annotation.Single

@Single
class CreateCustomerTable :
    Refactoring(
        executionOrder {
            ymd(2023, 8, 27)
            author("ben")
        },
        forward =
        changes {
            createTable("CUSTOMER") {
                column("CUST_ID", INT32)
                column("FIRSTNAME", Varchar(20))
                column("LASTNAME", Varchar(25)) {
                    nullable()
                }
                column("ADDRESS", Varchar(32))
                column("CITY", Varchar(20))
                column("STATE", Varchar(2))
                column("ZIP", Varchar(9))
                primaryKey("CUSTOMER_PK") {
                    column("CUST_ID")
                }
            }
            addNotNull {
                table {
                    name("CUSTOMER")
                }
                column("LASTNAME", Varchar(25))
            }
        },
        rollback =
        changes {
            dropTable("CUSTOMER")
        },
    )

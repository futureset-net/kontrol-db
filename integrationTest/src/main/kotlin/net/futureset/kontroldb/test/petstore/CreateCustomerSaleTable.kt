package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.KontrolDbDsl.Companion.changes
import net.futureset.kontroldb.KontrolDbDsl.Companion.executionOrder
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.StandardColumnTypes.Decimal
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.StandardColumnTypes.LOCALDATE
import org.koin.core.annotation.Single

@Single
class CreateCustomerSaleTable : Refactoring(
    executionOrder = executionOrder {
        ymd(2023, 9, 7)
        author("ben")
    },
    forward = changes {
        createTable {
            table("CUSTOMER_SALE")
            column("SALES_ID", INT_32)
            column("CUST_ID", INT_32)
            column("TOTAL_ITEM_AMOUNT", Decimal(10, 2))
            column("TAX_AMOUNT", Decimal(210, 25))
            column("TOTAL_SALE_AMOUNT", Decimal(10, 2))
            column("SALES_DATE", LOCALDATE)
            column("SHIPPING_HANDLING_FEE", Decimal(5, 2))
            primaryKey {
                column("SALES_ID")
                constraintName("CUSTOMER_SALE_PK")
            }
        }
        createIndex {
            table("CUSTOMER_SALE")
            column("CUST_ID")
            indexName("IX_CS_CUST_ID")
            tablespace("MY_INDEX_TS")
        }
        addForeignKey {
            table("CUSTOMER_SALE")
            foreignTable("CUSTOMER")
            columnMap("CUST_ID", "CUST_ID")
        }
    },
    rollback = changes {
        dropTable {
            table("CUSTOMER_SALE")
        }
    },
)

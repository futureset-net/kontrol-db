package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
import net.futureset.kontroldb.model.StandardColumnTypes.LOCALDATE
import net.futureset.kontroldb.modelchange.addForeignKey
import net.futureset.kontroldb.modelchange.createIndex
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.annotation.Single

@Single
class CreateCustomerSaleTable : Refactoring(
    executionOrder {
        ymd(2023, 9, 7)
        author("ben")
    },
    forward = changes {
        createTable {
            table("CUSTOMER_SALE")
            column("SALES_ID", INT_32)
            column("CUST_ID", INT_32)
            column("TOTAL_ITEM_AMOUNT", Decimal(10, 2))
            column("TAX_AMOUNT", Decimal(10, 2))
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

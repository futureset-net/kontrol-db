package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.KontrolDbDsl.Companion.changes
import net.futureset.kontroldb.KontrolDbDsl.Companion.executionOrder
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.StandardColumnTypes.Decimal
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import org.koin.core.annotation.Single

@Single
class CreateSalesItemTable : Refactoring(

    executionOrder = executionOrder {
        ymd(2023, 9, 9)
        author("ben")
        sequence(1)
    },
    forward = changes {
        createTable {
            table("SALES_ITEM")
            column("PRODUCT_ID", INT_32)
            column("SALES_ID", INT_32)
            column("SALE_AMOUNT", Decimal(10, 2))
        }
        addPrimaryKey {
            table("SALES_ITEM")
            constraintName("SALES_ITEM_PK")
            column("PRODUCT_ID")
            column("SALES_ID")
        }
        addForeignKey {
            table("SALES_ITEM")
            constraintName("FK_SI_PRODUCT")
            foreignTable("PRODUCT")
            columnMap("PRODUCT_ID", "ID")
        }
    },
    rollback = changes {
        dropTable {
            table("SALES_ITEM")
        }
    },
)

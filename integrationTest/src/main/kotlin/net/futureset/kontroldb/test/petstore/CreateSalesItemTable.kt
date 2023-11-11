package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.addForeignKey
import net.futureset.kontroldb.modelchange.addPrimaryKey
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.annotation.Single

@Single
class CreateSalesItemTable : Refactoring(

    executionOrder {
        ymd(2023, 9, 9)
        author("ben")
        sequence(1)
    },
    forward = changes {
        createTable("SALES_ITEM") {
            column("PRODUCT_ID", INT_32)
            column("SALES_ID", INT_32)
            column("SALE_AMOUNT", Decimal(10, 2))
        }
        addPrimaryKey("SALES_ITEM_PK") {
            table("SALES_ITEM")
            column("PRODUCT_ID")
            column("SALES_ID")
        }
        addForeignKey("FK_SI_PRODUCT") {
            table("SALES_ITEM")
            foreignTable("PRODUCT")
            referencing("PRODUCT_ID" to "ID")
        }
    },
    rollback = changes {
        dropTable("SALES_ITEM")
    },
)

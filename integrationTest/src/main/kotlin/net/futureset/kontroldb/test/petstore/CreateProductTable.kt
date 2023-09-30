package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.StandardColumnTypes.DATETIME
import net.futureset.kontroldb.StandardColumnTypes.Decimal
import net.futureset.kontroldb.StandardColumnTypes.INT_16
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.StandardColumnTypes.INT_64
import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.addPrimaryKey
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import org.koin.core.annotation.Single

@Single
class CreateProductTable : Refactoring(

    executionOrder {
        ymd(2023, 8, 27)
        author("ben")
        sequence(2)
    },
    forward = changes {
        createTable {
            table("PRODUCT")
            column("ID", INT_32)
            column("PRODUCT_NAME", Varchar(30))
            column("PACKAGE_ID", INT_64)
            column("CURRENT_INVENTORY_COUNT", INT_16)
            column("STORE_COST", Decimal(10, 2))
            column("SALE_PRICE", Decimal(10, 2))
            column("LAST_UPDATE_DATE", DATETIME)
            column("UPDATED_BY_USER", Varchar(30))
            column("PET_FLAG", BOOLEAN)
        }
        addPrimaryKey {
            table("PRODUCT")
            column("ID")
            constraintName("PRODUCT_PK")
        }
    },
    rollback = changes {
        dropTable {
            table("PRODUCT")
        }
    },
)

package net.futureset.kontroldb.test.petstore

import net.futureset.kontroldb.model.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.model.StandardColumnTypes.DATETIME
import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT16
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.INT64
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.addPrimaryKey
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.settings.DbDialect
import org.koin.core.annotation.Single

@Single
class CreateProductTable(private val dialect: DbDialect) : Refactoring(

    executionOrder {
        ymd(2023, 8, 27)
        author("ben")
        sequence(2)
    },
    forward = changes {
        createTable("PRODUCT") {
            column("ID", INT32)
            column("PRODUCT_NAME", Varchar(30))
            column("PACKAGE_ID", INT64)
            column("CURRENT_INVENTORY_COUNT", INT16)
            column("STORE_COST", Decimal(10, 2))
            column("SALE_PRICE", Decimal(10, 2))
            column("LAST_UPDATE_DATE", DATETIME) {
                defaultExpression(dialect.dbNowTimestamp())
            }
            column("UPDATED_BY_USER", Varchar(30))
            column("PET_FLAG", BOOLEAN)
        }
        addPrimaryKey("PRODUCT_PK") {
            table("PRODUCT")
            column("ID")
        }
    },
    rollback = changes {
        dropTable("PRODUCT")
    },
)

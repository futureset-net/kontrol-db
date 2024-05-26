package net.futureset.kontroldb.samples

import net.futureset.kontroldb.model.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.model.StandardColumnTypes.Char
import net.futureset.kontroldb.model.StandardColumnTypes.DATE
import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT16
import net.futureset.kontroldb.model.StandardColumnTypes.INT64
import net.futureset.kontroldb.model.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.applyDsvToTable
import net.futureset.kontroldb.refactoring.Refactoring

class LoadACsvFile : Refactoring(
    executionOrder {
        ymd(2023, 9, 16)
        author("ben")
        sequence(1)
    },
    forward = changes {
        applyDsvToTable {
            useDbLoadingTool(false)
            file("path/to/file.csv")
            insertRows(true)
            deleteRows(true)
            updateRows(true)
            ignoreInsertViolations(false)
            separator("|")
            table("CUSTOMER")
            columnMapping("CUST_ID", INT64, primaryKey = true)
            columnMapping("FIRSTNAME", Varchar(256))
            columnMapping("LASTNAME", Varchar(256))
            columnMapping("FAVOURITE_LETTER", Char(1))
            columnMapping("FAVOURITE_DECIMAL", Decimal(3, 2))
            columnMapping("IS_AN_IDIOT", BOOLEAN)
            columnMapping("NUMBER_OF_STAMPS", INT16)
            columnMapping("DATE_OF_BIRTH", DATE)
            columnMapping("TIME_RIGHT_NOW", LOCALDATETIME)
        }
    },
    rollback = emptyList(),

)

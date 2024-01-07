//[kontrol-db-core](../../index.md)/[net.futureset.kontroldb.modelchange](index.md)/[applyDsvToTable](apply-dsv-to-table.md)

# applyDsvToTable

[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.md).[applyDsvToTable](apply-dsv-to-table.md)(lambda: [ApplyDsvToTable.ApplyDsvToTableBuilder](-apply-dsv-to-table/-apply-dsv-to-table-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ApplyDsvToTable](-apply-dsv-to-table/index.md)

Load a delimiter separated file into a table

#### Receiver

[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.md) a container for a collection of changes

#### Return

[ApplyDsvToTable](-apply-dsv-to-table/index.md) the immutable data

#### Parameters

core engine and default templates for kontrol-db

| | |
|---|---|
| lambda | DSL to configure what to load |

#### Samples

```kotlin
import net.futureset.kontroldb.model.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.model.StandardColumnTypes.Char
import net.futureset.kontroldb.model.StandardColumnTypes.DATE
import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT_16
import net.futureset.kontroldb.model.StandardColumnTypes.INT_64
import net.futureset.kontroldb.model.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.applyDsvToTable
import net.futureset.kontroldb.refactoring.Refactoring
fun main() { 
   //sampleStart 
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
            columnMapping("CUST_ID", INT_64, primaryKey = true)
            columnMapping("FIRSTNAME", Varchar(256))
            columnMapping("LASTNAME", Varchar(256))
            columnMapping("FAVOURITE_LETTER", Char(1))
            columnMapping("FAVOURITE_DECIMAL", Decimal(3, 2))
            columnMapping("IS_AN_IDIOT", BOOLEAN)
            columnMapping("NUMBER_OF_STAMPS", INT_16)
            columnMapping("DATE_OF_BIRTH", DATE)
            columnMapping("TIME_RIGHT_NOW", LOCALDATETIME)
        }
    },
    rollback = emptyList(),

) 
   //sampleEnd
}
```

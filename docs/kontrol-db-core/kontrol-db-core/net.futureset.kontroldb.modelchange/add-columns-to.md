---
title: addColumnsTo
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[addColumnsTo](add-columns-to.html)



# addColumnsTo



[jvm]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[addColumnsTo](add-columns-to.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [AddColumns.AddColumnsBuilder](-add-columns/-add-columns-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AddColumns](-add-columns/index.html)



Add columns to a table



#### Receiver



a container for all the changes



#### Return



the immutable [AddColumns](-add-columns/index.html) type



#### Parameters


jvm

| | |
|---|---|
| tableName | name of the table to add columns |
| lambda | containing column definitions |



#### Samples

```kotlin
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.modelchange.addColumnsTo
import net.futureset.kontroldb.modelchange.dropColumnsFrom
import net.futureset.kontroldb.refactoring.Refactoring
fun main() { 
   //sampleStart 
   class AddAColumn(schemaName: String) : Refactoring(
    executionOrder {
        ymd(2023, 11, 24)
        author("ben")
        sequence(2)
    },
    forward = changes {
        addColumnsTo("FRED") {
            column("TWO_COLUMN", INT32)
            column("THREE_COLUMN", INT32)
            column("FOUR_COLUMN", INT32)
            column("FIVE_COLUMN", INT32)
            table {
                schema(schemaName)
            }
        }
        dropColumnsFrom("FRED") {
            column("FOUR_COLUMN")
            column("FIVE_COLUMN")
            table {
                schema(schemaName)
            }
        }
    },
    rollback = emptyList(),
) 
   //sampleEnd
}
```




---
title: dropTable
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[dropTable](drop-table.html)



# dropTable



[jvm]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[dropTable](drop-table.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [DropTable.DropTableBuilder](-drop-table/-drop-table-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DropTable](-drop-table/index.html)



Drop a table



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) container for the changes



#### Return



[DropTable](-drop-table/index.html) the immutable data needed to drop the table



#### Parameters


jvm

| | |
|---|---|
| tableName | the name of the table |
| lambda | other table attributes e.g. schema |



#### Samples

```kotlin
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.refactoring.Refactoring
fun main() { 
   //sampleStart 
   class CreateATable : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        createTable("MY_TABLE") {
            asLocalTemporaryTable() // make temporary
            column("CUST_ID", INT32)
            column("FIRSTNAME", Varchar(20))
            column("LASTNAME", Varchar(25))
            column("ADDRESS", Varchar(32))
            column("CITY", Varchar(20))
            column("STATE", Varchar(2))
            column("ZIP", Varchar(9))
            primaryKey("CUSTOMER_PK") {
                column("CUST_ID")
            }
        }
    },
    rollback = changes {
        dropTable("MY_TABLE")
    },
) 
   //sampleEnd
}
```




[jvm]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[dropTable](drop-table.html)(table: Table): [DropTable](-drop-table/index.html)





---
title: createTable
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[createTable](create-table.html)



# createTable



[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[createTable](create-table.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [CreateTable.CreateTableBuilder](-create-table/-create-table-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable](-create-table/index.html)



Create a table



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) the container for changes



#### Return



[CreateTable](-create-table/index.html) the immutable data for the table definition



#### Parameters


core engine and default templates for kontrol-db

| | |
|---|---|
| tableName | the name of the table |
| lambda | table columns and attributes |



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




[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[createTable](create-table.html)(table: Table, lambda: [CreateTable.CreateTableBuilder](-create-table/-create-table-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable](-create-table/index.html)





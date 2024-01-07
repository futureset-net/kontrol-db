//[kontrol-db-core](../../index.md)/[net.futureset.kontroldb.modelchange](index.md)/[createTable](create-table.md)

# createTable

[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.md).[createTable](create-table.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [CreateTable.CreateTableBuilder](-create-table/-create-table-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable](-create-table/index.md)

Create a table

#### Receiver

[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.md) the container for changes

#### Return

[CreateTable](-create-table/index.md) the immutable data for the table definition

#### Parameters

core engine and default templates for kontrol-db

| | |
|---|---|
| tableName | the name of the table |
| lambda | table columns and attributes |

#### Samples

```kotlin
import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
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
            column("CUST_ID", INT_32)
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
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.md).[createTable](create-table.md)(table: Table, lambda: [CreateTable.CreateTableBuilder](-create-table/-create-table-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable](-create-table/index.md)

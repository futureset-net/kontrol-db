---
title: dropRole
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[dropRole](drop-role.html)



# dropRole



[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[dropRole](drop-role.html)(roleName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DropRole](-drop-role/index.html)



Drop a database role



#### Return



[DropRole](-drop-role/index.html) the immutable role data



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) a container for a collection of model changes



#### Parameters


core engine and default templates for kontrol-db

| | |
|---|---|
| roleName | name of the role to drop |



#### Samples

```kotlin
import net.futureset.kontroldb.modelchange.createRole
import net.futureset.kontroldb.modelchange.dropRole
import net.futureset.kontroldb.refactoring.Refactoring
fun main() { 
   //sampleStart 
   class CreateARole : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        createRole("FRED")
    },
    rollback = changes {
        dropRole("FRED")
    },
) 
   //sampleEnd
}
```




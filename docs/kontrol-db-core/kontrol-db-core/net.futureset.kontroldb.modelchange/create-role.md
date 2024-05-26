---
title: createRole
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[createRole](create-role.html)



# createRole



[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[createRole](create-role.html)(roleName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CreateRole](-create-role/index.html)



Create a database role



#### Return



[CreateRole](-create-role/index.html) the immutable role data



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) a container for a collection of model changes



#### Parameters


core engine and default templates for kontrol-db

| | |
|---|---|
| roleName | name of the role to create |



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




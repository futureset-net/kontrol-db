---
title: dropSequenceIfExists
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[dropSequenceIfExists](drop-sequence-if-exists.html)



# dropSequenceIfExists



[jvm]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[dropSequenceIfExists](drop-sequence-if-exists.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})



Drop sequence if exists



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) container for a set of changes



#### Parameters


jvm

| | |
|---|---|
| name | the name of the sequence |
| lambda | sequence attributes |



#### Samples

```kotlin
import net.futureset.kontroldb.modelchange.createSequence
import net.futureset.kontroldb.modelchange.dropSequenceIfExists
import net.futureset.kontroldb.refactoring.Refactoring
fun main() { 
   //sampleStart 
   class CreateASequence : Refactoring(
    executionOrder {
        ymd(2023, 9, 13)
        author("ben")
    },
    forward = changes {
        createSequence("MY_SEQUENCE") {
            cache(10)
            cycle()
            incrementBy(2)
            startWith(3)
        }
    },
    rollback = changes {
        dropSequenceIfExists("MY_SEQUENCE")
    },
) 
   //sampleEnd
}
```




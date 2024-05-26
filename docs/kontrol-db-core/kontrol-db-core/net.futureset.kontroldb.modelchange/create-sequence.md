---
title: createSequence
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[createSequence](create-sequence.html)



# createSequence



[jvm]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[createSequence](create-sequence.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [CreateSequence.CreateSequenceBuilder](-create-sequence/-create-sequence-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateSequence](-create-sequence/index.html)



Create a sequence



#### Return



[CreateSequence](-create-sequence/index.html)



#### Parameters


jvm

| | |
|---|---|
| name | the name of the sequence to create |
| lambda | a block containing other sequence attributes |



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




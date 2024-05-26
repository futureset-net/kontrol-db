---
title: DatabaseConditionalChange
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DatabaseConditionalChange](index.html)



# DatabaseConditionalChange



[jvm]\
data class [DatabaseConditionalChange](index.html)(val dbPredicate: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val wrappedChange: [ModelChange](../-model-change/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DatabaseConditionalChange](-database-conditional-change.html) | [jvm]<br>constructor(dbPredicate: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), wrappedChange: [ModelChange](../-model-change/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [dbPredicate](db-predicate.html) | [jvm]<br>val [dbPredicate](db-predicate.html): ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [wrappedChange](wrapped-change.html) | [jvm]<br>val [wrappedChange](wrapped-change.html): [ModelChange](../-model-change/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

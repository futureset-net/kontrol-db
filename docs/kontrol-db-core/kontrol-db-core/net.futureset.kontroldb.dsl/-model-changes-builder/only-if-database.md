---
title: onlyIfDatabase
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.dsl](../index.html)/[ModelChangesBuilder](index.html)/[onlyIfDatabase](only-if-database.html)



# onlyIfDatabase



[jvm]\
fun [ModelChange](../../net.futureset.kontroldb.modelchange/-model-change/index.html).[onlyIfDatabase](only-if-database.html)(dbPredicate: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [DatabaseConditionalChange](../../net.futureset.kontroldb.modelchange/-database-conditional-change/index.html)



For onlyIfDatabase



#### Receiver



[ModelChange](../../net.futureset.kontroldb.modelchange/-model-change/index.html) used to modify a change within a refactoring



#### Return



[DatabaseConditionalChange](../../net.futureset.kontroldb.modelchange/-database-conditional-change/index.html) wrap change in condition



#### Parameters


jvm

| | |
|---|---|
| dbPredicate | expression to match database name |





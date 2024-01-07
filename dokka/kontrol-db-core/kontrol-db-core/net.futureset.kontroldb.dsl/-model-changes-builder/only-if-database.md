//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.dsl](../index.md)/[ModelChangesBuilder](index.md)/[onlyIfDatabase](only-if-database.md)

# onlyIfDatabase

[core engine and default templates for kontrol-db]\
fun [ModelChange](../../net.futureset.kontroldb.modelchange/-model-change/index.md).[onlyIfDatabase](only-if-database.md)(dbPredicate: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [DatabaseConditionalChange](../../net.futureset.kontroldb.modelchange/-database-conditional-change/index.md)

For onlyIfDatabase

#### Receiver

[ModelChange](../../net.futureset.kontroldb.modelchange/-model-change/index.md) used to modify a change within a refactoring

#### Return

[DatabaseConditionalChange](../../net.futureset.kontroldb.modelchange/-database-conditional-change/index.md) wrap change in condition

#### Parameters

core engine and default templates for kontrol-db

| | |
|---|---|
| dbPredicate | expression to match database name |

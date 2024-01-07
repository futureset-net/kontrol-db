//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[DatabaseConditionalChange](index.md)

# DatabaseConditionalChange

[core engine and default templates for kontrol-db]\
data class [DatabaseConditionalChange](index.md)(val dbPredicate: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val wrappedChange: [ModelChange](../-model-change/index.md)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [DatabaseConditionalChange](-database-conditional-change.md) | [core engine and default templates for kontrol-db]<br>constructor(dbPredicate: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), wrappedChange: [ModelChange](../-model-change/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [dbPredicate](db-predicate.md) | [core engine and default templates for kontrol-db]<br>val [dbPredicate](db-predicate.md): ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [wrappedChange](wrapped-change.md) | [core engine and default templates for kontrol-db]<br>val [wrappedChange](wrapped-change.md): [ModelChange](../-model-change/index.md) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[Not](index.md)

# Not

[core engine and default templates for kontrol-db]\
data class [Not](index.md)(val predicate: [SqlPredicate](../-sql-predicate/index.md)) : [SqlPredicate](../-sql-predicate/index.md)

## Constructors

| | |
|---|---|
| [Not](-not.md) | [core engine and default templates for kontrol-db]<br>constructor(predicate: [SqlPredicate](../-sql-predicate/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [predicate](predicate.md) | [core engine and default templates for kontrol-db]<br>val [predicate](predicate.md): [SqlPredicate](../-sql-predicate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.md) | [core engine and default templates for kontrol-db]<br>open fun [isEmpty](../-sql-predicate/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.md) | [core engine and default templates for kontrol-db]<br>open operator fun [plus](../-sql-predicate/plus.md)(predicate: [SqlPredicate](../-sql-predicate/index.md)): [SqlPredicate](../-sql-predicate/index.md) |
| [toSql](to-sql.md) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.md)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

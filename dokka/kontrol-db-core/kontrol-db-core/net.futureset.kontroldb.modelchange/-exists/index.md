//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[Exists](index.md)

# Exists

[core engine and default templates for kontrol-db]\
data class [Exists](index.md)(selectQuery: [SelectQuery](../-select-query/index.md)) : [SqlPredicate](../-sql-predicate/index.md)

## Constructors

| | |
|---|---|
| [Exists](-exists.md) | [core engine and default templates for kontrol-db]<br>constructor(selectQuery: [SelectQuery](../-select-query/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.md) | [core engine and default templates for kontrol-db]<br>open fun [isEmpty](../-sql-predicate/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.md) | [core engine and default templates for kontrol-db]<br>open operator fun [plus](../-sql-predicate/plus.md)(predicate: [SqlPredicate](../-sql-predicate/index.md)): [SqlPredicate](../-sql-predicate/index.md) |
| [toSql](to-sql.md) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.md)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

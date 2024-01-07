//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[AnyOf](index.md)

# AnyOf

[core engine and default templates for kontrol-db]\
data class [AnyOf](index.md)(val predicates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SqlPredicate](../-sql-predicate/index.md)&gt;) : [SqlPredicate](../-sql-predicate/index.md)

## Constructors

| | |
|---|---|
| [AnyOf](-any-of.md) | [core engine and default templates for kontrol-db]<br>constructor(predicates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SqlPredicate](../-sql-predicate/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [predicates](predicates.md) | [core engine and default templates for kontrol-db]<br>val [predicates](predicates.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SqlPredicate](../-sql-predicate/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [isEmpty](is-empty.md) | [core engine and default templates for kontrol-db]<br>open override fun [isEmpty](is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](plus.md) | [core engine and default templates for kontrol-db]<br>open operator override fun [plus](plus.md)(predicate: [SqlPredicate](../-sql-predicate/index.md)): [AnyOf](index.md) |
| [toSql](to-sql.md) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.md)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

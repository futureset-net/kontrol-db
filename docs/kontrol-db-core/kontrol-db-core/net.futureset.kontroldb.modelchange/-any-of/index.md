---
title: AnyOf
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[AnyOf](index.html)



# AnyOf



[core engine and default templates for kontrol-db]\
data class [AnyOf](index.html)(val predicates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SqlPredicate](../-sql-predicate/index.html)&gt;) : [SqlPredicate](../-sql-predicate/index.html)



## Constructors


| | |
|---|---|
| [AnyOf](-any-of.html) | [core engine and default templates for kontrol-db]<br>constructor(predicates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SqlPredicate](../-sql-predicate/index.html)&gt;) |


## Properties


| Name | Summary |
|---|---|
| [predicates](predicates.html) | [core engine and default templates for kontrol-db]<br>val [predicates](predicates.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SqlPredicate](../-sql-predicate/index.html)&gt; |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](is-empty.html) | [core engine and default templates for kontrol-db]<br>open override fun [isEmpty](is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](plus.html) | [core engine and default templates for kontrol-db]<br>open operator override fun [plus](plus.html)(predicate: [SqlPredicate](../-sql-predicate/index.html)): [AnyOf](index.html) |
| [toSql](to-sql.html) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


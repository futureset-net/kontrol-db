---
title: Not
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[Not](index.html)



# Not



[core engine and default templates for kontrol-db]\
data class [Not](index.html)(val predicate: [SqlPredicate](../-sql-predicate/index.html)) : [SqlPredicate](../-sql-predicate/index.html)



## Constructors


| | |
|---|---|
| [Not](-not.html) | [core engine and default templates for kontrol-db]<br>constructor(predicate: [SqlPredicate](../-sql-predicate/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [predicate](predicate.html) | [core engine and default templates for kontrol-db]<br>val [predicate](predicate.html): [SqlPredicate](../-sql-predicate/index.html) |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.html) | [core engine and default templates for kontrol-db]<br>open fun [isEmpty](../-sql-predicate/is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.html) | [core engine and default templates for kontrol-db]<br>open operator fun [plus](../-sql-predicate/plus.html)(predicate: [SqlPredicate](../-sql-predicate/index.html)): [SqlPredicate](../-sql-predicate/index.html) |
| [toSql](to-sql.html) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


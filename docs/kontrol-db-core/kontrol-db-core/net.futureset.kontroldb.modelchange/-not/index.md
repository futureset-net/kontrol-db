---
title: Not
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[Not](index.html)



# Not



[jvm]\
data class [Not](index.html)(val predicate: [SqlPredicate](../-sql-predicate/index.html)) : [SqlPredicate](../-sql-predicate/index.html)



## Constructors


| | |
|---|---|
| [Not](-not.html) | [jvm]<br>constructor(predicate: [SqlPredicate](../-sql-predicate/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [predicate](predicate.html) | [jvm]<br>val [predicate](predicate.html): [SqlPredicate](../-sql-predicate/index.html) |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.html) | [jvm]<br>open fun [isEmpty](../-sql-predicate/is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.html) | [jvm]<br>open operator fun [plus](../-sql-predicate/plus.html)(predicate: [SqlPredicate](../-sql-predicate/index.html)): [SqlPredicate](../-sql-predicate/index.html) |
| [toSql](to-sql.html) | [jvm]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


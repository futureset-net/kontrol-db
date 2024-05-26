---
title: Exists
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[Exists](index.html)



# Exists



[jvm]\
data class [Exists](index.html)(selectQuery: [SelectQuery](../-select-query/index.html)) : [SqlPredicate](../-sql-predicate/index.html)



## Constructors


| | |
|---|---|
| [Exists](-exists.html) | [jvm]<br>constructor(selectQuery: [SelectQuery](../-select-query/index.html)) |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.html) | [jvm]<br>open fun [isEmpty](../-sql-predicate/is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.html) | [jvm]<br>open operator fun [plus](../-sql-predicate/plus.html)(predicate: [SqlPredicate](../-sql-predicate/index.html)): [SqlPredicate](../-sql-predicate/index.html) |
| [toSql](to-sql.html) | [jvm]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


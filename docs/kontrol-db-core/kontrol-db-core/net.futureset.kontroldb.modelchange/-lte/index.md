---
title: Lte
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[Lte](index.html)



# Lte



[jvm]\
data class [Lte](index.html)(val a: [Operand](../-operand/index.html), val b: [Operand](../-operand/index.html)) : [SqlPredicate](../-sql-predicate/index.html)



## Constructors


| | |
|---|---|
| [Lte](-lte.html) | [jvm]<br>constructor(a: [Operand](../-operand/index.html), b: [Operand](../-operand/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [a](a.html) | [jvm]<br>val [a](a.html): [Operand](../-operand/index.html) |
| [b](b.html) | [jvm]<br>val [b](b.html): [Operand](../-operand/index.html) |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.html) | [jvm]<br>open fun [isEmpty](../-sql-predicate/is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.html) | [jvm]<br>open operator fun [plus](../-sql-predicate/plus.html)(predicate: [SqlPredicate](../-sql-predicate/index.html)): [SqlPredicate](../-sql-predicate/index.html) |
| [toSql](to-sql.html) | [jvm]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


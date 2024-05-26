---
title: Gt
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[Gt](index.html)



# Gt



[core engine and default templates for kontrol-db]\
data class [Gt](index.html)(val a: [Operand](../-operand/index.html), val b: [Operand](../-operand/index.html)) : [SqlPredicate](../-sql-predicate/index.html)



## Constructors


| | |
|---|---|
| [Gt](-gt.html) | [core engine and default templates for kontrol-db]<br>constructor(a: [Operand](../-operand/index.html), b: [Operand](../-operand/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [a](a.html) | [core engine and default templates for kontrol-db]<br>val [a](a.html): [Operand](../-operand/index.html) |
| [b](b.html) | [core engine and default templates for kontrol-db]<br>val [b](b.html): [Operand](../-operand/index.html) |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.html) | [core engine and default templates for kontrol-db]<br>open fun [isEmpty](../-sql-predicate/is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.html) | [core engine and default templates for kontrol-db]<br>open operator fun [plus](../-sql-predicate/plus.html)(predicate: [SqlPredicate](../-sql-predicate/index.html)): [SqlPredicate](../-sql-predicate/index.html) |
| [toSql](to-sql.html) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


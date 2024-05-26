---
title: SqlPredicate
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[SqlPredicate](index.html)



# SqlPredicate

interface [SqlPredicate](index.html) : SqlString

#### Inheritors


| |
|---|
| [AllOf](../-all-of/index.html) |
| [AnyOf](../-any-of/index.html) |
| [Not](../-not/index.html) |
| [Exists](../-exists/index.html) |
| [Eq](../-eq/index.html) |
| [Gt](../-gt/index.html) |
| [Lt](../-lt/index.html) |
| [Lte](../-lte/index.html) |
| [Gte](../-gte/index.html) |
| [Between](../-between/index.html) |


## Functions


| Name | Summary |
|---|---|
| [isEmpty](is-empty.html) | [jvm]<br>open fun [isEmpty](is-empty.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](plus.html) | [jvm]<br>open operator fun [plus](plus.html)(predicate: [SqlPredicate](index.html)): [SqlPredicate](index.html) |
| [toSql](../-operand/index.html#1035997089%2FFunctions%2F1904592438) | [jvm]<br>abstract fun [toSql](../-operand/index.html#1035997089%2FFunctions%2F1904592438)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[SqlPredicate](index.md)

# SqlPredicate

interface [SqlPredicate](index.md) : SqlString

#### Inheritors

| |
|---|
| [AllOf](../-all-of/index.md) |
| [AnyOf](../-any-of/index.md) |
| [Not](../-not/index.md) |
| [Exists](../-exists/index.md) |
| [Eq](../-eq/index.md) |
| [Gt](../-gt/index.md) |
| [Lt](../-lt/index.md) |
| [Lte](../-lte/index.md) |
| [Gte](../-gte/index.md) |
| [Between](../-between/index.md) |

## Functions

| Name | Summary |
|---|---|
| [isEmpty](is-empty.md) | [core engine and default templates for kontrol-db]<br>open fun [isEmpty](is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](plus.md) | [core engine and default templates for kontrol-db]<br>open operator fun [plus](plus.md)(predicate: [SqlPredicate](index.md)): [SqlPredicate](index.md) |
| [toSql](../-operand/index.md#1035997089%2FFunctions%2F1815734191) | [core engine and default templates for kontrol-db]<br>abstract fun [toSql](../-operand/index.md#1035997089%2FFunctions%2F1815734191)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[Between](index.md)

# Between

[core engine and default templates for kontrol-db]\
data class [Between](index.md)(val subject: [Operand](../-operand/index.md), val a: [Operand](../-operand/index.md), val b: [Operand](../-operand/index.md)) : [SqlPredicate](../-sql-predicate/index.md)

## Constructors

| | |
|---|---|
| [Between](-between.md) | [core engine and default templates for kontrol-db]<br>constructor(subject: [Operand](../-operand/index.md), a: [Operand](../-operand/index.md), b: [Operand](../-operand/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [a](a.md) | [core engine and default templates for kontrol-db]<br>val [a](a.md): [Operand](../-operand/index.md) |
| [b](b.md) | [core engine and default templates for kontrol-db]<br>val [b](b.md): [Operand](../-operand/index.md) |
| [subject](subject.md) | [core engine and default templates for kontrol-db]<br>val [subject](subject.md): [Operand](../-operand/index.md) |

## Functions

| Name | Summary |
|---|---|
| [isEmpty](../-sql-predicate/is-empty.md) | [core engine and default templates for kontrol-db]<br>open fun [isEmpty](../-sql-predicate/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [plus](../-sql-predicate/plus.md) | [core engine and default templates for kontrol-db]<br>open operator fun [plus](../-sql-predicate/plus.md)(predicate: [SqlPredicate](../-sql-predicate/index.md)): [SqlPredicate](../-sql-predicate/index.md) |
| [toSql](to-sql.md) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.md)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

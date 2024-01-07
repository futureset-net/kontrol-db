//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[ValuesBuilder](index.md)

# ValuesBuilder

[core engine and default templates for kontrol-db]\
class [ValuesBuilder](index.md) : Builder&lt;[ValuesBuilder](index.md), [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;

## Constructors

| | |
|---|---|
| [ValuesBuilder](-values-builder.md) | [core engine and default templates for kontrol-db]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt; |
| [value](value.md) | [core engine and default templates for kontrol-db]<br>fun [value](value.md)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)): [ValuesBuilder](index.md)<br>fun [value](value.md)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html)): [ValuesBuilder](index.md)<br>fun [value](value.md)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ValuesBuilder](index.md)<br>fun [value](value.md)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [ValuesBuilder](index.md)<br>fun [value](value.md)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ValuesBuilder](index.md) |
| [valueExpression](value-expression.md) | [core engine and default templates for kontrol-db]<br>fun [valueExpression](value-expression.md)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ValuesBuilder](index.md) |

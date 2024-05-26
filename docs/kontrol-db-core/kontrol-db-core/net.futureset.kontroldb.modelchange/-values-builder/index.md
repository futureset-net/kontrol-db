---
title: ValuesBuilder
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[ValuesBuilder](index.html)



# ValuesBuilder



[core engine and default templates for kontrol-db]\
class [ValuesBuilder](index.html) : Builder&lt;[ValuesBuilder](index.html), [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;



## Constructors


| | |
|---|---|
| [ValuesBuilder](-values-builder.html) | [core engine and default templates for kontrol-db]<br>constructor() |


## Functions


| Name | Summary |
|---|---|
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt; |
| [value](value.html) | [core engine and default templates for kontrol-db]<br>fun [value](value.html)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)): [ValuesBuilder](index.html)<br>fun [value](value.html)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html)): [ValuesBuilder](index.html)<br>fun [value](value.html)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ValuesBuilder](index.html)<br>fun [value](value.html)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [ValuesBuilder](index.html)<br>fun [value](value.html)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ValuesBuilder](index.html) |
| [valueExpression](value-expression.html) | [core engine and default templates for kontrol-db]<br>fun [valueExpression](value-expression.html)(colName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), v: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ValuesBuilder](index.html) |


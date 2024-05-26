---
title: PredicateBuilder
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[PredicateBuilder](index.html)



# PredicateBuilder



[jvm]\
class [PredicateBuilder](index.html)(predicate: [SqlPredicate](../-sql-predicate/index.html) = AllOf(predicates = emptyList())) : Builder&lt;[PredicateBuilder](index.html), [SqlPredicate](../-sql-predicate/index.html)&gt; , [SqlValueFactory](../-sql-value-factory/index.html)



## Constructors


| | |
|---|---|
| [PredicateBuilder](-predicate-builder.html) | [jvm]<br>constructor(predicate: [SqlPredicate](../-sql-predicate/index.html) = AllOf(predicates = emptyList())) |


## Functions


| Name | Summary |
|---|---|
| [allOf](all-of.html) | [jvm]<br>fun [allOf](all-of.html)(lambda: [PredicateBuilder](index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [PredicateBuilder](index.html) |
| [anyOf](any-of.html) | [jvm]<br>fun [anyOf](any-of.html)(lambda: [PredicateBuilder](index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [PredicateBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [SqlPredicate](../-sql-predicate/index.html) |
| [col](../-sql-value-factory/col.html) | [jvm]<br>open fun [col](../-sql-value-factory/col.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): DbIdentifier |
| [column](../-sql-value-factory/column.html) | [jvm]<br>open fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[column](../-sql-value-factory/column.html)(): DbIdentifier |
| [eq](eq.html) | [jvm]<br>infix fun [Operand](../-operand/index.html).[eq](eq.html)(operand: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[eq](eq.html)(operand: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[eq](eq.html)(operand: [Operand](../-operand/index.html)): [Operand](../-operand/index.html) |
| [existsSelect](exists-select.html) | [jvm]<br>fun [existsSelect](exists-select.html)(vararg columnNames: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [SelectQuery.SelectQueryBuilder](../-select-query/-select-query-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [PredicateBuilder](index.html) |
| [expression](../-sql-value-factory/expression.html) | [jvm]<br>open fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[expression](../-sql-value-factory/expression.html)(): ColumnValue |
| [gt](gt.html) | [jvm]<br>infix fun [Operand](../-operand/index.html).[gt](gt.html)(operand: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[gt](gt.html)(operand: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[gt](gt.html)(operand: [Operand](../-operand/index.html)): [Operand](../-operand/index.html) |
| [gte](gte.html) | [jvm]<br>infix fun [Operand](../-operand/index.html).[gte](gte.html)(operand: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[gte](gte.html)(operand: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[gte](gte.html)(operand: [Operand](../-operand/index.html)): [Operand](../-operand/index.html) |
| [inRangeOf](in-range-of.html) | [jvm]<br>infix fun [Operand](../-operand/index.html).[inRangeOf](in-range-of.html)(operand: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): [Operand](../-operand/index.html) |
| [literal](../-sql-value-factory/literal.html) | [jvm]<br>open fun [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html).[literal](../-sql-value-factory/literal.html)(): ColumnValue<br>open fun [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html).[literal](../-sql-value-factory/literal.html)(): ColumnValue<br>open fun [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html).[literal](../-sql-value-factory/literal.html)(): ColumnValue<br>open fun [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html).[literal](../-sql-value-factory/literal.html)(): ColumnValue<br>open fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[literal](../-sql-value-factory/literal.html)(): ColumnValue |
| [lt](lt.html) | [jvm]<br>infix fun [Operand](../-operand/index.html).[lt](lt.html)(operand: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[lt](lt.html)(operand: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[lt](lt.html)(operand: [Operand](../-operand/index.html)): [Operand](../-operand/index.html) |
| [lte](lte.html) | [jvm]<br>infix fun [Operand](../-operand/index.html).[lte](lte.html)(operand: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[lte](lte.html)(operand: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Operand](../-operand/index.html)<br>infix fun [Operand](../-operand/index.html).[lte](lte.html)(operand: [Operand](../-operand/index.html)): [Operand](../-operand/index.html) |
| [not](not.html) | [jvm]<br>fun [not](not.html)(lambda: [PredicateBuilder](index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [PredicateBuilder](index.html) |


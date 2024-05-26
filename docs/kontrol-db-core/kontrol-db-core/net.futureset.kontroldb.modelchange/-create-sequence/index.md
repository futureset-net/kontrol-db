---
title: CreateSequence
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[CreateSequence](index.html)



# CreateSequence



[core engine and default templates for kontrol-db]\
data class [CreateSequence](index.html)(val schemaObject: SchemaObject, val startWith: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val incrementBy: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val minValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val maxValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val cycle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, val cache: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val columnType: ColumnType?) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [CreateSequence](-create-sequence.html) | [core engine and default templates for kontrol-db]<br>constructor(schemaObject: SchemaObject, startWith: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), incrementBy: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), minValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, maxValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, cycle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, cache: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), columnType: ColumnType?) |


## Types


| Name | Summary |
|---|---|
| [CreateSequenceBuilder](-create-sequence-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [CreateSequenceBuilder](-create-sequence-builder/index.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : Builder&lt;[CreateSequence.CreateSequenceBuilder](-create-sequence-builder/index.html), [CreateSequence](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [cache](cache.html) | [core engine and default templates for kontrol-db]<br>val [cache](cache.html): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [columnType](column-type.html) | [core engine and default templates for kontrol-db]<br>val [columnType](column-type.html): ColumnType? |
| [cycle](cycle.html) | [core engine and default templates for kontrol-db]<br>val [cycle](cycle.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? |
| [incrementBy](increment-by.html) | [core engine and default templates for kontrol-db]<br>val [incrementBy](increment-by.html): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [maxValue](max-value.html) | [core engine and default templates for kontrol-db]<br>val [maxValue](max-value.html): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [minValue](min-value.html) | [core engine and default templates for kontrol-db]<br>val [minValue](min-value.html): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [schemaObject](schema-object.html) | [core engine and default templates for kontrol-db]<br>val [schemaObject](schema-object.html): SchemaObject |
| [startWith](start-with.html) | [core engine and default templates for kontrol-db]<br>val [startWith](start-with.html): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


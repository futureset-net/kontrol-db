//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[CreateSequence](index.md)

# CreateSequence

[core engine and default templates for kontrol-db]\
data class [CreateSequence](index.md)(val schemaObject: SchemaObject, val startWith: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val incrementBy: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val minValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val maxValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val cycle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, val cache: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val columnType: ColumnType?) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [CreateSequence](-create-sequence.md) | [core engine and default templates for kontrol-db]<br>constructor(schemaObject: SchemaObject, startWith: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), incrementBy: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), minValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, maxValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, cycle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, cache: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), columnType: ColumnType?) |

## Types

| Name | Summary |
|---|---|
| [CreateSequenceBuilder](-create-sequence-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [CreateSequenceBuilder](-create-sequence-builder/index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : Builder&lt;[CreateSequence.CreateSequenceBuilder](-create-sequence-builder/index.md), [CreateSequence](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [cache](cache.md) | [core engine and default templates for kontrol-db]<br>val [cache](cache.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [columnType](column-type.md) | [core engine and default templates for kontrol-db]<br>val [columnType](column-type.md): ColumnType? |
| [cycle](cycle.md) | [core engine and default templates for kontrol-db]<br>val [cycle](cycle.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? |
| [incrementBy](increment-by.md) | [core engine and default templates for kontrol-db]<br>val [incrementBy](increment-by.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [maxValue](max-value.md) | [core engine and default templates for kontrol-db]<br>val [maxValue](max-value.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [minValue](min-value.md) | [core engine and default templates for kontrol-db]<br>val [minValue](min-value.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [schemaObject](schema-object.md) | [core engine and default templates for kontrol-db]<br>val [schemaObject](schema-object.md): SchemaObject |
| [startWith](start-with.md) | [core engine and default templates for kontrol-db]<br>val [startWith](start-with.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

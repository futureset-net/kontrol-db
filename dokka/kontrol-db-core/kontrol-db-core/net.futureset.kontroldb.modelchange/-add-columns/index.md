//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[AddColumns](index.md)

# AddColumns

[core engine and default templates for kontrol-db]\
data class [AddColumns](index.md)(val table: SchemaObject, val columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [AddColumns](-add-columns.md) | [core engine and default templates for kontrol-db]<br>constructor(table: SchemaObject, columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;) |

## Types

| Name | Summary |
|---|---|
| [AddColumnsBuilder](-add-columns-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [AddColumnsBuilder](-add-columns-builder/index.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf()) : Builder&lt;[AddColumns.AddColumnsBuilder](-add-columns-builder/index.md), [AddColumns](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columnDefinitions](column-definitions.md) | [core engine and default templates for kontrol-db]<br>val [columnDefinitions](column-definitions.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt; |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): SchemaObject |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

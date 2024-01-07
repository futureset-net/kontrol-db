//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[DropColumns](index.md)

# DropColumns

[core engine and default templates for kontrol-db]\
data class [DropColumns](index.md)(val table: SchemaObject, val columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [DropColumns](-drop-columns.md) | [core engine and default templates for kontrol-db]<br>constructor(table: SchemaObject, columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;) |

## Types

| Name | Summary |
|---|---|
| [DropColumnsBuilder](-drop-columns-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [DropColumnsBuilder](-drop-columns-builder/index.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : Builder&lt;[DropColumns.DropColumnsBuilder](-drop-columns-builder/index.md), [DropColumns](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columns](columns.md) | [core engine and default templates for kontrol-db]<br>val [columns](columns.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): SchemaObject |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[InsertRows](index.md)

# InsertRows

[core engine and default templates for kontrol-db]\
data class [InsertRows](index.md)(val table: Table, val columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, val fromSelect: [SelectQuery](../-select-query/index.md)?) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [InsertRows](-insert-rows.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, fromSelect: [SelectQuery](../-select-query/index.md)?) |

## Types

| Name | Summary |
|---|---|
| [InsertRowsBuilder](-insert-rows-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [InsertRowsBuilder](-insert-rows-builder/index.md)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), fromSelect: [SelectQuery](../-select-query/index.md)? = null) : [TableAliasBuilder](../-table-alias-builder/index.md)&lt;[InsertRows.InsertRowsBuilder](-insert-rows-builder/index.md), [InsertRows](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columnValues](column-values.md) | [core engine and default templates for kontrol-db]<br>val [columnValues](column-values.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; |
| [fromSelect](from-select.md) | [core engine and default templates for kontrol-db]<br>val [fromSelect](from-select.md): [SelectQuery](../-select-query/index.md)? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

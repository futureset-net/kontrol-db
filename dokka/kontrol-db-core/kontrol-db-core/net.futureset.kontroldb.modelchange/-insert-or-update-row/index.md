//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[InsertOrUpdateRow](index.md)

# InsertOrUpdateRow

[core engine and default templates for kontrol-db]\
data class [InsertOrUpdateRow](index.md)(val table: Table, val columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, val primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val updateMode: [UpdateMode](../-update-mode/index.md)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [InsertOrUpdateRow](-insert-or-update-row.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, updateMode: [UpdateMode](../-update-mode/index.md)) |

## Types

| Name | Summary |
|---|---|
| [InsertOrUpdateRowBuilder](-insert-or-update-row-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [InsertOrUpdateRowBuilder](-insert-or-update-row-builder/index.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), primaryKeys: [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;DbIdentifier&gt; = mutableSetOf(), updateMode: [UpdateMode](../-update-mode/index.md) = UpdateMode.UPDATE_AND_INSERT) : [TableBuilder](../-table-builder/index.md)&lt;[InsertOrUpdateRow.InsertOrUpdateRowBuilder](-insert-or-update-row-builder/index.md), [InsertOrUpdateRow](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columnValues](column-values.md) | [core engine and default templates for kontrol-db]<br>val [columnValues](column-values.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; |
| [primaryKeys](primary-keys.md) | [core engine and default templates for kontrol-db]<br>val [primaryKeys](primary-keys.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |
| [updateMode](update-mode.md) | [core engine and default templates for kontrol-db]<br>val [updateMode](update-mode.md): [UpdateMode](../-update-mode/index.md) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

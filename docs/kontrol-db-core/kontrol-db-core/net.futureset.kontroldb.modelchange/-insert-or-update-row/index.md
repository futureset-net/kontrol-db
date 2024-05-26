---
title: InsertOrUpdateRow
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[InsertOrUpdateRow](index.html)



# InsertOrUpdateRow



[core engine and default templates for kontrol-db]\
data class [InsertOrUpdateRow](index.html)(val table: Table, val columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, val primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val updateMode: [UpdateMode](../-update-mode/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [InsertOrUpdateRow](-insert-or-update-row.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, updateMode: [UpdateMode](../-update-mode/index.html)) |


## Types


| Name | Summary |
|---|---|
| [InsertOrUpdateRowBuilder](-insert-or-update-row-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [InsertOrUpdateRowBuilder](-insert-or-update-row-builder/index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), primaryKeys: [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;DbIdentifier&gt; = mutableSetOf(), updateMode: [UpdateMode](../-update-mode/index.html) = UpdateMode.UPDATE_AND_INSERT) : [TableBuilder](../-table-builder/index.html)&lt;[InsertOrUpdateRow.InsertOrUpdateRowBuilder](-insert-or-update-row-builder/index.html), [InsertOrUpdateRow](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columnValues](column-values.html) | [core engine and default templates for kontrol-db]<br>val [columnValues](column-values.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; |
| [primaryKeys](primary-keys.html) | [core engine and default templates for kontrol-db]<br>val [primaryKeys](primary-keys.html): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |
| [updateMode](update-mode.html) | [core engine and default templates for kontrol-db]<br>val [updateMode](update-mode.html): [UpdateMode](../-update-mode/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


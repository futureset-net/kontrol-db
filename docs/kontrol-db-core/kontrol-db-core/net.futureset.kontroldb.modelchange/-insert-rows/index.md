---
title: InsertRows
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[InsertRows](index.html)



# InsertRows



[core engine and default templates for kontrol-db]\
data class [InsertRows](index.html)(val table: Table, val columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, val fromSelect: [SelectQuery](../-select-query/index.html)?) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [InsertRows](-insert-rows.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;, fromSelect: [SelectQuery](../-select-query/index.html)?) |


## Types


| Name | Summary |
|---|---|
| [InsertRowsBuilder](-insert-rows-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [InsertRowsBuilder](-insert-rows-builder/index.html)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), fromSelect: [SelectQuery](../-select-query/index.html)? = null) : [TableAliasBuilder](../-table-alias-builder/index.html)&lt;[InsertRows.InsertRowsBuilder](-insert-rows-builder/index.html), [InsertRows](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columnValues](column-values.html) | [core engine and default templates for kontrol-db]<br>val [columnValues](column-values.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; |
| [fromSelect](from-select.html) | [core engine and default templates for kontrol-db]<br>val [fromSelect](from-select.html): [SelectQuery](../-select-query/index.html)? |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


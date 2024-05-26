---
title: UpdateRows
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[UpdateRows](index.html)



# UpdateRows



[core engine and default templates for kontrol-db]\
data class [UpdateRows](index.html)(val table: [TableAlias](../-table-alias/index.html), val columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, val predicate: [SqlPredicate](../-sql-predicate/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [UpdateRows](-update-rows.html) | [core engine and default templates for kontrol-db]<br>constructor(table: [TableAlias](../-table-alias/index.html), columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, predicate: [SqlPredicate](../-sql-predicate/index.html)) |


## Types


| Name | Summary |
|---|---|
| [UpdateRowsBuilder](-update-rows-builder/index.html) | [core engine and default templates for kontrol-db]<br>data class [UpdateRowsBuilder](-update-rows-builder/index.html)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, ColumnValue&gt; = mutableMapOf(), predicate: [SqlPredicate](../-sql-predicate/index.html) = AllOf(emptyList())) : [TableAliasBuilder](../-table-alias-builder/index.html)&lt;[UpdateRows.UpdateRowsBuilder](-update-rows-builder/index.html), [UpdateRows](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columnValues](column-values.html) | [core engine and default templates for kontrol-db]<br>val [columnValues](column-values.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt; |
| [predicate](predicate.html) | [core engine and default templates for kontrol-db]<br>val [predicate](predicate.html): [SqlPredicate](../-sql-predicate/index.html) |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): [TableAlias](../-table-alias/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


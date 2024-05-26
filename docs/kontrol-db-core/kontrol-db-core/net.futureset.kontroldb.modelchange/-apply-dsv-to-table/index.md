---
title: ApplyDsvToTable
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[ApplyDsvToTable](index.html)



# ApplyDsvToTable



[core engine and default templates for kontrol-db]\
data class [ApplyDsvToTable](index.html)(val table: Table, val file: Resource, val useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val headerMappings: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt;, val primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [ApplyDsvToTable](-apply-dsv-to-table.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, file: Resource, useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), headerMappings: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt;, primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |


## Types


| Name | Summary |
|---|---|
| [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.html) : [TableBuilder](../-table-builder/index.html)&lt;[ApplyDsvToTable.ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.html), [ApplyDsvToTable](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [deleteRows](delete-rows.html) | [core engine and default templates for kontrol-db]<br>val [deleteRows](delete-rows.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [file](file.html) | [core engine and default templates for kontrol-db]<br>val [file](file.html): Resource |
| [headerMappings](header-mappings.html) | [core engine and default templates for kontrol-db]<br>val [headerMappings](header-mappings.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt; |
| [ignoreInsertViolations](ignore-insert-violations.html) | [core engine and default templates for kontrol-db]<br>val [ignoreInsertViolations](ignore-insert-violations.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [insertRows](insert-rows.html) | [core engine and default templates for kontrol-db]<br>val [insertRows](insert-rows.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [primaryKeys](primary-keys.html) | [core engine and default templates for kontrol-db]<br>val [primaryKeys](primary-keys.html): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [separator](separator.html) | [core engine and default templates for kontrol-db]<br>val [separator](separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |
| [updateRows](update-rows.html) | [core engine and default templates for kontrol-db]<br>val [updateRows](update-rows.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [useDbLoadingTool](use-db-loading-tool.html) | [core engine and default templates for kontrol-db]<br>val [useDbLoadingTool](use-db-loading-tool.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](checksum.html) | [core engine and default templates for kontrol-db]<br>open override fun [checksum](checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


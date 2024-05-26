---
title: ApplyDsvToTable
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[ApplyDsvToTable](index.html)



# ApplyDsvToTable



[jvm]\
data class [ApplyDsvToTable](index.html)(val table: Table, val file: Resource, val useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val headerMappings: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt;, val primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [ApplyDsvToTable](-apply-dsv-to-table.html) | [jvm]<br>constructor(table: Table, file: Resource, useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), headerMappings: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt;, primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |


## Types


| Name | Summary |
|---|---|
| [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.html) | [jvm]<br>class [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.html) : [TableBuilder](../-table-builder/index.html)&lt;[ApplyDsvToTable.ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.html), [ApplyDsvToTable](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [deleteRows](delete-rows.html) | [jvm]<br>val [deleteRows](delete-rows.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [file](file.html) | [jvm]<br>val [file](file.html): Resource |
| [headerMappings](header-mappings.html) | [jvm]<br>val [headerMappings](header-mappings.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt; |
| [ignoreInsertViolations](ignore-insert-violations.html) | [jvm]<br>val [ignoreInsertViolations](ignore-insert-violations.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [insertRows](insert-rows.html) | [jvm]<br>val [insertRows](insert-rows.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [primaryKeys](primary-keys.html) | [jvm]<br>val [primaryKeys](primary-keys.html): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [separator](separator.html) | [jvm]<br>val [separator](separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [table](table.html) | [jvm]<br>val [table](table.html): Table |
| [updateRows](update-rows.html) | [jvm]<br>val [updateRows](update-rows.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [useDbLoadingTool](use-db-loading-tool.html) | [jvm]<br>val [useDbLoadingTool](use-db-loading-tool.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](checksum.html) | [jvm]<br>open override fun [checksum](checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[ApplyDsvToTable](index.md)

# ApplyDsvToTable

[core engine and default templates for kontrol-db]\
data class [ApplyDsvToTable](index.md)(val table: Table, val file: Resource, val useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val headerMappings: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt;, val primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [ApplyDsvToTable](-apply-dsv-to-table.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, file: Resource, useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), headerMappings: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt;, primaryKeys: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Types

| Name | Summary |
|---|---|
| [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.md) : [TableBuilder](../-table-builder/index.md)&lt;[ApplyDsvToTable.ApplyDsvToTableBuilder](-apply-dsv-to-table-builder/index.md), [ApplyDsvToTable](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [deleteRows](delete-rows.md) | [core engine and default templates for kontrol-db]<br>val [deleteRows](delete-rows.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [file](file.md) | [core engine and default templates for kontrol-db]<br>val [file](file.md): Resource |
| [headerMappings](header-mappings.md) | [core engine and default templates for kontrol-db]<br>val [headerMappings](header-mappings.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnDefinition&gt; |
| [ignoreInsertViolations](ignore-insert-violations.md) | [core engine and default templates for kontrol-db]<br>val [ignoreInsertViolations](ignore-insert-violations.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [insertRows](insert-rows.md) | [core engine and default templates for kontrol-db]<br>val [insertRows](insert-rows.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [primaryKeys](primary-keys.md) | [core engine and default templates for kontrol-db]<br>val [primaryKeys](primary-keys.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [separator](separator.md) | [core engine and default templates for kontrol-db]<br>val [separator](separator.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |
| [updateRows](update-rows.md) | [core engine and default templates for kontrol-db]<br>val [updateRows](update-rows.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [useDbLoadingTool](use-db-loading-tool.md) | [core engine and default templates for kontrol-db]<br>val [useDbLoadingTool](use-db-loading-tool.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](checksum.md) | [core engine and default templates for kontrol-db]<br>open override fun [checksum](checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

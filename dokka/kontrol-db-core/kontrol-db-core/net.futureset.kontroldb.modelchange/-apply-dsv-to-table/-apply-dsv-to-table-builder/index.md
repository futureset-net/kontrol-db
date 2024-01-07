//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[ApplyDsvToTable](../index.md)/[ApplyDsvToTableBuilder](index.md)

# ApplyDsvToTableBuilder

[core engine and default templates for kontrol-db]\
class [ApplyDsvToTableBuilder](index.md) : [TableBuilder](../../-table-builder/index.md)&lt;[ApplyDsvToTable.ApplyDsvToTableBuilder](index.md), [ApplyDsvToTable](../index.md)&gt;

## Constructors

| | |
|---|---|
| [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder.md) | [core engine and default templates for kontrol-db]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [ApplyDsvToTable](../index.md) |
| [columnMapping](column-mapping.md) | [core engine and default templates for kontrol-db]<br>fun [columnMapping](column-mapping.md)(columnName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnType: ColumnType, headerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, primaryKey: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [deleteRows](delete-rows.md) | [core engine and default templates for kontrol-db]<br>fun [deleteRows](delete-rows.md)(deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [file](file.md) | [core engine and default templates for kontrol-db]<br>fun [file](file.md)(file: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [ignoreInsertViolations](ignore-insert-violations.md) | [core engine and default templates for kontrol-db]<br>fun [ignoreInsertViolations](ignore-insert-violations.md)(ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [insertRows](insert-rows.md) | [core engine and default templates for kontrol-db]<br>fun [insertRows](insert-rows.md)(insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [separator](separator.md) | [core engine and default templates for kontrol-db]<br>fun [separator](separator.md)(separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [updateRows](update-rows.md) | [core engine and default templates for kontrol-db]<br>fun [updateRows](update-rows.md)(updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |
| [useDbLoadingTool](use-db-loading-tool.md) | [core engine and default templates for kontrol-db]<br>fun [useDbLoadingTool](use-db-loading-tool.md)(useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.md) |

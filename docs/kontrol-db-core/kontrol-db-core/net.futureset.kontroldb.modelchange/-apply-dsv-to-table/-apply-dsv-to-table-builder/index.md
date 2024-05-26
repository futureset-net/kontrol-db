---
title: ApplyDsvToTableBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[ApplyDsvToTable](../index.html)/[ApplyDsvToTableBuilder](index.html)



# ApplyDsvToTableBuilder



[jvm]\
class [ApplyDsvToTableBuilder](index.html) : [TableBuilder](../../-table-builder/index.html)&lt;[ApplyDsvToTable.ApplyDsvToTableBuilder](index.html), [ApplyDsvToTable](../index.html)&gt;



## Constructors


| | |
|---|---|
| [ApplyDsvToTableBuilder](-apply-dsv-to-table-builder.html) | [jvm]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [ApplyDsvToTable](../index.html) |
| [columnMapping](column-mapping.html) | [jvm]<br>fun [columnMapping](column-mapping.html)(columnName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnType: ColumnType, headerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, primaryKey: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [deleteRows](delete-rows.html) | [jvm]<br>fun [deleteRows](delete-rows.html)(deleteRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [file](file.html) | [jvm]<br>fun [file](file.html)(file: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [ignoreInsertViolations](ignore-insert-violations.html) | [jvm]<br>fun [ignoreInsertViolations](ignore-insert-violations.html)(ignoreInsertViolations: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [insertRows](insert-rows.html) | [jvm]<br>fun [insertRows](insert-rows.html)(insertRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [separator](separator.html) | [jvm]<br>fun [separator](separator.html)(separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [updateRows](update-rows.html) | [jvm]<br>fun [updateRows](update-rows.html)(updateRows: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |
| [useDbLoadingTool](use-db-loading-tool.html) | [jvm]<br>fun [useDbLoadingTool](use-db-loading-tool.html)(useDbLoadingTool: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [ApplyDsvToTable.ApplyDsvToTableBuilder](index.html) |

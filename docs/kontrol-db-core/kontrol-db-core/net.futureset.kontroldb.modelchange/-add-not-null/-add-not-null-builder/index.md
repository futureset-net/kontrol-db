---
title: AddNotNullBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[AddNotNull](../index.html)/[AddNotNullBuilder](index.html)



# AddNotNullBuilder



[core engine and default templates for kontrol-db]\
class [AddNotNullBuilder](index.html) : [TableBuilder](../../-table-builder/index.html)&lt;[AddNotNull.AddNotNullBuilder](index.html), [AddNotNull](../index.html)&gt;



## Constructors


| | |
|---|---|
| [AddNotNullBuilder](-add-not-null-builder.html) | [core engine and default templates for kontrol-db]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [AddNotNull.AddNotNullBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [AddNotNull.AddNotNullBuilder](index.html) |
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [AddNotNull](../index.html) |
| [column](column.html) | [core engine and default templates for kontrol-db]<br>fun [column](column.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: ColumnType, block: ColumnDefinition.ColumnDefinitionBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { }): [AddNotNull.AddNotNullBuilder](index.html) |
| [table](../../-table-builder/table.html) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.html)(table: Table): [AddNotNull.AddNotNullBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [AddNotNull.AddNotNullBuilder](index.html) |


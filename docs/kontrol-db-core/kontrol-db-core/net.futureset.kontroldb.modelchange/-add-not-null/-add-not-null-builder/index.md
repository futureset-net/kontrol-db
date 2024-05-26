---
title: AddNotNullBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[AddNotNull](../index.html)/[AddNotNullBuilder](index.html)



# AddNotNullBuilder



[jvm]\
class [AddNotNullBuilder](index.html) : [TableBuilder](../../-table-builder/index.html)&lt;[AddNotNull.AddNotNullBuilder](index.html), [AddNotNull](../index.html)&gt;



## Constructors


| | |
|---|---|
| [AddNotNullBuilder](-add-not-null-builder.html) | [jvm]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [AddNotNull.AddNotNullBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [AddNotNull.AddNotNullBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [AddNotNull](../index.html) |
| [column](column.html) | [jvm]<br>fun [column](column.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: ColumnType, block: ColumnDefinition.ColumnDefinitionBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { }): [AddNotNull.AddNotNullBuilder](index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [AddNotNull.AddNotNullBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [AddNotNull.AddNotNullBuilder](index.html) |


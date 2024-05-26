---
title: AddColumnsBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[AddColumns](../index.html)/[AddColumnsBuilder](index.html)



# AddColumnsBuilder



[jvm]\
class [AddColumnsBuilder](index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf()) : Builder&lt;[AddColumns.AddColumnsBuilder](index.html), [AddColumns](../index.html)&gt;



## Constructors


| | |
|---|---|
| [AddColumnsBuilder](-add-columns-builder.html) | [jvm]<br>constructor(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf()) |


## Functions


| Name | Summary |
|---|---|
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [AddColumns](../index.html) |
| [column](column.html) | [jvm]<br>fun [column](column.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: ColumnType, block: ColumnDefinition.ColumnDefinitionBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { }): [AddColumns.AddColumnsBuilder](index.html) |
| [table](table.html) | [jvm]<br>fun [table](table.html)(lambda: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AddColumns.AddColumnsBuilder](index.html) |


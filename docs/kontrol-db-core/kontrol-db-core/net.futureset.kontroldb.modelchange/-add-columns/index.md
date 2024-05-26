---
title: AddColumns
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[AddColumns](index.html)



# AddColumns



[jvm]\
data class [AddColumns](index.html)(val table: SchemaObject, val columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [AddColumns](-add-columns.html) | [jvm]<br>constructor(table: SchemaObject, columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;) |


## Types


| Name | Summary |
|---|---|
| [AddColumnsBuilder](-add-columns-builder/index.html) | [jvm]<br>class [AddColumnsBuilder](-add-columns-builder/index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf()) : Builder&lt;[AddColumns.AddColumnsBuilder](-add-columns-builder/index.html), [AddColumns](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columnDefinitions](column-definitions.html) | [jvm]<br>val [columnDefinitions](column-definitions.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt; |
| [table](table.html) | [jvm]<br>val [table](table.html): SchemaObject |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

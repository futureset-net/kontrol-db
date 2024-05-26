---
title: DropColumns
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DropColumns](index.html)



# DropColumns



[jvm]\
data class [DropColumns](index.html)(val table: SchemaObject, val columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DropColumns](-drop-columns.html) | [jvm]<br>constructor(table: SchemaObject, columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;) |


## Types


| Name | Summary |
|---|---|
| [DropColumnsBuilder](-drop-columns-builder/index.html) | [jvm]<br>class [DropColumnsBuilder](-drop-columns-builder/index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : Builder&lt;[DropColumns.DropColumnsBuilder](-drop-columns-builder/index.html), [DropColumns](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columns](columns.html) | [jvm]<br>val [columns](columns.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [table](table.html) | [jvm]<br>val [table](table.html): SchemaObject |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


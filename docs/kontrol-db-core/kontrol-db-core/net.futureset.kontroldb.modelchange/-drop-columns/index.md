---
title: DropColumns
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DropColumns](index.html)



# DropColumns



[core engine and default templates for kontrol-db]\
data class [DropColumns](index.html)(val table: SchemaObject, val columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DropColumns](-drop-columns.html) | [core engine and default templates for kontrol-db]<br>constructor(table: SchemaObject, columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;) |


## Types


| Name | Summary |
|---|---|
| [DropColumnsBuilder](-drop-columns-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [DropColumnsBuilder](-drop-columns-builder/index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : Builder&lt;[DropColumns.DropColumnsBuilder](-drop-columns-builder/index.html), [DropColumns](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columns](columns.html) | [core engine and default templates for kontrol-db]<br>val [columns](columns.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): SchemaObject |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


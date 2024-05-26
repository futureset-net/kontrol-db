---
title: CreateIndex
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[CreateIndex](index.html)



# CreateIndex



[core engine and default templates for kontrol-db]\
data class [CreateIndex](index.html)(val table: Table, val columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, val clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val indexName: DbIdentifier? = null, val tablespace: Tablespace? = null) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [CreateIndex](-create-index.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), indexName: DbIdentifier? = null, tablespace: Tablespace? = null) |


## Types


| Name | Summary |
|---|---|
| [CreateIndexBuilder](-create-index-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [CreateIndexBuilder](-create-index-builder/index.html)(indexName: DbIdentifier? = null, tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : [TableBuilder](../-table-builder/index.html)&lt;[CreateIndex.CreateIndexBuilder](-create-index-builder/index.html), [CreateIndex](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [clustered](clustered.html) | [core engine and default templates for kontrol-db]<br>val [clustered](clustered.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [columnReferences](column-references.html) | [core engine and default templates for kontrol-db]<br>val [columnReferences](column-references.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [indexName](index-name.html) | [core engine and default templates for kontrol-db]<br>val [indexName](index-name.html): DbIdentifier? = null |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |
| [tablespace](tablespace.html) | [core engine and default templates for kontrol-db]<br>val [tablespace](tablespace.html): Tablespace? = null |
| [unique](unique.html) | [core engine and default templates for kontrol-db]<br>val [unique](unique.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


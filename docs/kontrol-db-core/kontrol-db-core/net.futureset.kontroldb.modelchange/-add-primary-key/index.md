---
title: AddPrimaryKey
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[AddPrimaryKey](index.html)



# AddPrimaryKey



[core engine and default templates for kontrol-db]\
data class [AddPrimaryKey](index.html)(val table: Table, val columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, val clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, val inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), var constraintName: DbIdentifier? = null) : [ConstraintModelChange](../-constraint-model-change/index.html)



## Constructors


| | |
|---|---|
| [AddPrimaryKey](-add-primary-key.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), constraintName: DbIdentifier? = null) |


## Types


| Name | Summary |
|---|---|
| [AddPrimaryKeyBuilder](-add-primary-key-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [AddPrimaryKeyBuilder](-add-primary-key-builder/index.html)(constraintName: DbIdentifier? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf(), clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [TableBuilder](../-table-builder/index.html)&lt;[AddPrimaryKey.AddPrimaryKeyBuilder](-add-primary-key-builder/index.html), [AddPrimaryKey](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [clustered](clustered.html) | [core engine and default templates for kontrol-db]<br>val [clustered](clustered.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? |
| [columnReferences](column-references.html) | [core engine and default templates for kontrol-db]<br>val [columnReferences](column-references.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [constraintName](constraint-name.html) | [core engine and default templates for kontrol-db]<br>open override var [constraintName](constraint-name.html): DbIdentifier? |
| [inline](inline.html) | [core engine and default templates for kontrol-db]<br>val [inline](inline.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


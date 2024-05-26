---
title: AddForeignKey
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[AddForeignKey](index.html)



# AddForeignKey



[jvm]\
data class [AddForeignKey](index.html)(val table: Table?, val foreignTable: SchemaObject, val columnMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, DbIdentifier&gt;, val constraintName: DbIdentifier?) : [ConstraintModelChange](../-constraint-model-change/index.html)



## Constructors


| | |
|---|---|
| [AddForeignKey](-add-foreign-key.html) | [jvm]<br>constructor(table: Table?, foreignTable: SchemaObject, columnMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, DbIdentifier&gt;, constraintName: DbIdentifier?) |


## Types


| Name | Summary |
|---|---|
| [AddForeignKeyBuilder](-add-foreign-key-builder/index.html) | [jvm]<br>data class [AddForeignKeyBuilder](-add-foreign-key-builder/index.html)(constraintName: DbIdentifier? = null, foreignTable: SchemaObject? = null, columnMap: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; = mutableMapOf()) : [TableBuilder](../-table-builder/index.html)&lt;[AddForeignKey.AddForeignKeyBuilder](-add-foreign-key-builder/index.html), [AddForeignKey](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columnMap](column-map.html) | [jvm]<br>val [columnMap](column-map.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; |
| [constraintName](constraint-name.html) | [jvm]<br>open override val [constraintName](constraint-name.html): DbIdentifier? |
| [foreignTable](foreign-table.html) | [jvm]<br>val [foreignTable](foreign-table.html): SchemaObject |
| [table](table.html) | [jvm]<br>val [table](table.html): Table? |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


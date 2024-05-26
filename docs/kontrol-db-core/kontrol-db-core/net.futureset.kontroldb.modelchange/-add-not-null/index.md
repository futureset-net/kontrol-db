---
title: AddNotNull
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[AddNotNull](index.html)



# AddNotNull



[jvm]\
data class [AddNotNull](index.html)(val table: Table?, val column: ColumnDefinition, val constraintName: DbIdentifier?) : [ConstraintModelChange](../-constraint-model-change/index.html)



## Constructors


| | |
|---|---|
| [AddNotNull](-add-not-null.html) | [jvm]<br>constructor(table: Table?, column: ColumnDefinition, constraintName: DbIdentifier?) |


## Types


| Name | Summary |
|---|---|
| [AddNotNullBuilder](-add-not-null-builder/index.html) | [jvm]<br>class [AddNotNullBuilder](-add-not-null-builder/index.html) : [TableBuilder](../-table-builder/index.html)&lt;[AddNotNull.AddNotNullBuilder](-add-not-null-builder/index.html), [AddNotNull](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [column](column.html) | [jvm]<br>val [column](column.html): ColumnDefinition |
| [constraintName](constraint-name.html) | [jvm]<br>open override val [constraintName](constraint-name.html): DbIdentifier? |
| [table](table.html) | [jvm]<br>val [table](table.html): Table? |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


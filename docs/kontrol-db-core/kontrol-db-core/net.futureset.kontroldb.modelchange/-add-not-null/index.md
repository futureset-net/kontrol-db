---
title: AddNotNull
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[AddNotNull](index.html)



# AddNotNull



[core engine and default templates for kontrol-db]\
data class [AddNotNull](index.html)(val table: Table?, val column: ColumnDefinition, val constraintName: DbIdentifier?) : [ConstraintModelChange](../-constraint-model-change/index.html)



## Constructors


| | |
|---|---|
| [AddNotNull](-add-not-null.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table?, column: ColumnDefinition, constraintName: DbIdentifier?) |


## Types


| Name | Summary |
|---|---|
| [AddNotNullBuilder](-add-not-null-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [AddNotNullBuilder](-add-not-null-builder/index.html) : [TableBuilder](../-table-builder/index.html)&lt;[AddNotNull.AddNotNullBuilder](-add-not-null-builder/index.html), [AddNotNull](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [column](column.html) | [core engine and default templates for kontrol-db]<br>val [column](column.html): ColumnDefinition |
| [constraintName](constraint-name.html) | [core engine and default templates for kontrol-db]<br>open override val [constraintName](constraint-name.html): DbIdentifier? |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table? |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


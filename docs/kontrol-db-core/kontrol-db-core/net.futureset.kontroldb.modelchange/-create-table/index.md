---
title: CreateTable
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[CreateTable](index.html)



# CreateTable



[core engine and default templates for kontrol-db]\
data class [CreateTable](index.html)(val table: Table, val columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;, val tablespace: Tablespace?, val primaryKey: [AddPrimaryKey](../-add-primary-key/index.html)?, val fromSelect: [SelectQuery](../-select-query/index.html)?, val preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [CreateTable](-create-table.html) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;, tablespace: Tablespace?, primaryKey: [AddPrimaryKey](../-add-primary-key/index.html)?, fromSelect: [SelectQuery](../-select-query/index.html)?, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |


## Types


| Name | Summary |
|---|---|
| [CreateTableBuilder](-create-table-builder/index.html) | [core engine and default templates for kontrol-db]<br>data class [CreateTableBuilder](-create-table-builder/index.html)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf(), primaryKey: [AddPrimaryKey](../-add-primary-key/index.html)? = null, selectFrom: [SelectQuery](../-select-query/index.html)? = null, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [TableBuilder](../-table-builder/index.html)&lt;[CreateTable.CreateTableBuilder](-create-table-builder/index.html), [CreateTable](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columnDefinitions](column-definitions.html) | [core engine and default templates for kontrol-db]<br>val [columnDefinitions](column-definitions.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt; |
| [fromSelect](from-select.html) | [core engine and default templates for kontrol-db]<br>val [fromSelect](from-select.html): [SelectQuery](../-select-query/index.html)? |
| [preserveRowsOnCommit](preserve-rows-on-commit.html) | [core engine and default templates for kontrol-db]<br>val [preserveRowsOnCommit](preserve-rows-on-commit.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [primaryKey](primary-key.html) | [core engine and default templates for kontrol-db]<br>val [primaryKey](primary-key.html): [AddPrimaryKey](../-add-primary-key/index.html)? |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |
| [tablespace](tablespace.html) | [core engine and default templates for kontrol-db]<br>val [tablespace](tablespace.html): Tablespace? |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


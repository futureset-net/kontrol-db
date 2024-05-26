---
title: CreateTableBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[CreateTable](../index.html)/[CreateTableBuilder](index.html)



# CreateTableBuilder



[core engine and default templates for kontrol-db]\
data class [CreateTableBuilder](index.html)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf(), primaryKey: [AddPrimaryKey](../../-add-primary-key/index.html)? = null, selectFrom: [SelectQuery](../../-select-query/index.html)? = null, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [TableBuilder](../../-table-builder/index.html)&lt;[CreateTable.CreateTableBuilder](index.html), [CreateTable](../index.html)&gt;



## Constructors


| | |
|---|---|
| [CreateTableBuilder](-create-table-builder.html) | [core engine and default templates for kontrol-db]<br>constructor(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf(), primaryKey: [AddPrimaryKey](../../-add-primary-key/index.html)? = null, selectFrom: [SelectQuery](../../-select-query/index.html)? = null, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [CreateTable.CreateTableBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [CreateTable.CreateTableBuilder](index.html) |
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [CreateTable](../index.html) |
| [column](column.html) | [core engine and default templates for kontrol-db]<br>fun [column](column.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: ColumnType, block: ColumnDefinition.ColumnDefinitionBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { }): [CreateTable.CreateTableBuilder](index.html) |
| [primaryKey](primary-key.html) | [core engine and default templates for kontrol-db]<br>fun [primaryKey](primary-key.html)(constraintName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [AddPrimaryKey.AddPrimaryKeyBuilder](../../-add-primary-key/-add-primary-key-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable.CreateTableBuilder](index.html) |
| [selectFrom](select-from.html) | [core engine and default templates for kontrol-db]<br>fun [selectFrom](select-from.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [SelectQuery.SelectQueryBuilder](../../-select-query/-select-query-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable.CreateTableBuilder](index.html) |
| [table](../../-table-builder/table.html) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.html)(table: Table): [CreateTable.CreateTableBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [CreateTable.CreateTableBuilder](index.html) |
| [tablespace](tablespace.html) | [core engine and default templates for kontrol-db]<br>fun [tablespace](tablespace.html)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CreateTable.CreateTableBuilder](index.html) |


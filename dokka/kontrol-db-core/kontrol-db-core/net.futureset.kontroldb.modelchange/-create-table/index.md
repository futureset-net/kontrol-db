//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[CreateTable](index.md)

# CreateTable

[core engine and default templates for kontrol-db]\
data class [CreateTable](index.md)(val table: Table, val columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;, val tablespace: Tablespace?, val primaryKey: [AddPrimaryKey](../-add-primary-key/index.md)?, val fromSelect: [SelectQuery](../-select-query/index.md)?, val preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [CreateTable](-create-table.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnDefinitions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt;, tablespace: Tablespace?, primaryKey: [AddPrimaryKey](../-add-primary-key/index.md)?, fromSelect: [SelectQuery](../-select-query/index.md)?, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Types

| Name | Summary |
|---|---|
| [CreateTableBuilder](-create-table-builder/index.md) | [core engine and default templates for kontrol-db]<br>data class [CreateTableBuilder](-create-table-builder/index.md)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf(), primaryKey: [AddPrimaryKey](../-add-primary-key/index.md)? = null, selectFrom: [SelectQuery](../-select-query/index.md)? = null, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [TableBuilder](../-table-builder/index.md)&lt;[CreateTable.CreateTableBuilder](-create-table-builder/index.md), [CreateTable](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columnDefinitions](column-definitions.md) | [core engine and default templates for kontrol-db]<br>val [columnDefinitions](column-definitions.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnDefinition&gt; |
| [fromSelect](from-select.md) | [core engine and default templates for kontrol-db]<br>val [fromSelect](from-select.md): [SelectQuery](../-select-query/index.md)? |
| [preserveRowsOnCommit](preserve-rows-on-commit.md) | [core engine and default templates for kontrol-db]<br>val [preserveRowsOnCommit](preserve-rows-on-commit.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [primaryKey](primary-key.md) | [core engine and default templates for kontrol-db]<br>val [primaryKey](primary-key.md): [AddPrimaryKey](../-add-primary-key/index.md)? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |
| [tablespace](tablespace.md) | [core engine and default templates for kontrol-db]<br>val [tablespace](tablespace.md): Tablespace? |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

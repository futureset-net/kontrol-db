//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[CreateTable](../index.md)/[CreateTableBuilder](index.md)

# CreateTableBuilder

[core engine and default templates for kontrol-db]\
data class [CreateTableBuilder](index.md)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf(), primaryKey: [AddPrimaryKey](../../-add-primary-key/index.md)? = null, selectFrom: [SelectQuery](../../-select-query/index.md)? = null, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [TableBuilder](../../-table-builder/index.md)&lt;[CreateTable.CreateTableBuilder](index.md), [CreateTable](../index.md)&gt;

## Constructors

| | |
|---|---|
| [CreateTableBuilder](-create-table-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf(), primaryKey: [AddPrimaryKey](../../-add-primary-key/index.md)? = null, selectFrom: [SelectQuery](../../-select-query/index.md)? = null, preserveRowsOnCommit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [CreateTable.CreateTableBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [CreateTable.CreateTableBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [CreateTable](../index.md) |
| [column](column.md) | [core engine and default templates for kontrol-db]<br>fun [column](column.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: ColumnType, block: ColumnDefinition.ColumnDefinitionBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { }): [CreateTable.CreateTableBuilder](index.md) |
| [primaryKey](primary-key.md) | [core engine and default templates for kontrol-db]<br>fun [primaryKey](primary-key.md)(constraintName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [AddPrimaryKey.AddPrimaryKeyBuilder](../../-add-primary-key/-add-primary-key-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable.CreateTableBuilder](index.md) |
| [selectFrom](select-from.md) | [core engine and default templates for kontrol-db]<br>fun [selectFrom](select-from.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [SelectQuery.SelectQueryBuilder](../../-select-query/-select-query-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateTable.CreateTableBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [CreateTable.CreateTableBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [CreateTable.CreateTableBuilder](index.md) |
| [tablespace](tablespace.md) | [core engine and default templates for kontrol-db]<br>fun [tablespace](tablespace.md)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CreateTable.CreateTableBuilder](index.md) |

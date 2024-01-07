//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[InsertRows](../index.md)/[InsertRowsBuilder](index.md)

# InsertRowsBuilder

[core engine and default templates for kontrol-db]\
class [InsertRowsBuilder](index.md)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), fromSelect: [SelectQuery](../../-select-query/index.md)? = null) : [TableAliasBuilder](../../-table-alias-builder/index.md)&lt;[InsertRows.InsertRowsBuilder](index.md), [InsertRows](../index.md)&gt;

## Constructors

| | |
|---|---|
| [InsertRowsBuilder](-insert-rows-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), fromSelect: [SelectQuery](../../-select-query/index.md)? = null) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [core engine and default templates for kontrol-db]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | [core engine and default templates for kontrol-db]<br>open override var [alias](alias.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [addRows](add-rows.md) | [core engine and default templates for kontrol-db]<br>fun [addRows](add-rows.md)(rows: [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;): [InsertRows.InsertRowsBuilder](index.md) |
| [alias](../../-table-alias-builder/alias.md) | [core engine and default templates for kontrol-db]<br>open fun [alias](../../-table-alias-builder/alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertRows.InsertRowsBuilder](index.md) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [InsertRows.InsertRowsBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [InsertRows.InsertRowsBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [InsertRows](../index.md) |
| [fromQuery](from-query.md) | [core engine and default templates for kontrol-db]<br>fun [fromQuery](from-query.md)(lambda: [SelectQuery.SelectQueryBuilder](../../-select-query/-select-query-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InsertRows.InsertRowsBuilder](index.md) |
| [row](row.md) | [core engine and default templates for kontrol-db]<br>fun [row](row.md)(lambda: [ValuesBuilder](../../-values-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InsertRows.InsertRowsBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [InsertRows.InsertRowsBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [InsertRows.InsertRowsBuilder](index.md) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.md) | [core engine and default templates for kontrol-db]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertRows.InsertRowsBuilder](index.md)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertRows.InsertRowsBuilder](index.md) |

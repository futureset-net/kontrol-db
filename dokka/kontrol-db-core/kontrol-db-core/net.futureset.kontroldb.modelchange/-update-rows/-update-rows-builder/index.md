//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[UpdateRows](../index.md)/[UpdateRowsBuilder](index.md)

# UpdateRowsBuilder

[core engine and default templates for kontrol-db]\
data class [UpdateRowsBuilder](index.md)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, ColumnValue&gt; = mutableMapOf(), predicate: [SqlPredicate](../../-sql-predicate/index.md) = AllOf(emptyList())) : [TableAliasBuilder](../../-table-alias-builder/index.md)&lt;[UpdateRows.UpdateRowsBuilder](index.md), [UpdateRows](../index.md)&gt;

## Constructors

| | |
|---|---|
| [UpdateRowsBuilder](-update-rows-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, ColumnValue&gt; = mutableMapOf(), predicate: [SqlPredicate](../../-sql-predicate/index.md) = AllOf(emptyList())) |

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
| [alias](../../-table-alias-builder/alias.md) | [core engine and default templates for kontrol-db]<br>open fun [alias](../../-table-alias-builder/alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [UpdateRows.UpdateRowsBuilder](index.md) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [UpdateRows.UpdateRowsBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [UpdateRows.UpdateRowsBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [UpdateRows](../index.md) |
| [set](set.md) | [core engine and default templates for kontrol-db]<br>fun [set](set.md)(value: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnValue&gt;) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [UpdateRows.UpdateRowsBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [UpdateRows.UpdateRowsBuilder](index.md) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.md) | [core engine and default templates for kontrol-db]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [UpdateRows.UpdateRowsBuilder](index.md)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [UpdateRows.UpdateRowsBuilder](index.md) |
| [where](where.md) | [core engine and default templates for kontrol-db]<br>fun [where](where.md)(lambda: [PredicateBuilder](../../-predicate-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [UpdateRows.UpdateRowsBuilder](index.md) |

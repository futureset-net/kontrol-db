//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[DeleteRows](../index.md)/[DeleteRowsBuilder](index.md)

# DeleteRowsBuilder

[core engine and default templates for kontrol-db]\
class [DeleteRowsBuilder](index.md)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var predicate: [SqlPredicate](../../-sql-predicate/index.md) = AllOf(emptyList())) : [TableAliasBuilder](../../-table-alias-builder/index.md)&lt;[DeleteRows.DeleteRowsBuilder](index.md), [DeleteRows](../index.md)&gt;

## Constructors

| | |
|---|---|
| [DeleteRowsBuilder](-delete-rows-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, predicate: [SqlPredicate](../../-sql-predicate/index.md) = AllOf(emptyList())) |

## Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | [core engine and default templates for kontrol-db]<br>open override var [alias](alias.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [predicate](predicate.md) | [core engine and default templates for kontrol-db]<br>var [predicate](predicate.md): [SqlPredicate](../../-sql-predicate/index.md) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [alias](../../-table-alias-builder/alias.md) | [core engine and default templates for kontrol-db]<br>open fun [alias](../../-table-alias-builder/alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DeleteRows.DeleteRowsBuilder](index.md) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [DeleteRows.DeleteRowsBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [DeleteRows.DeleteRowsBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [DeleteRows](../index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [DeleteRows.DeleteRowsBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DeleteRows.DeleteRowsBuilder](index.md) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.md) | [core engine and default templates for kontrol-db]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DeleteRows.DeleteRowsBuilder](index.md)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DeleteRows.DeleteRowsBuilder](index.md) |
| [where](where.md) | [core engine and default templates for kontrol-db]<br>fun [where](where.md)(lambda: [PredicateBuilder](../../-predicate-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [DeleteRows.DeleteRowsBuilder](index.md) |

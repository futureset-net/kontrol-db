//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[SelectQuery](../index.md)/[SelectQueryBuilder](index.md)

# SelectQueryBuilder

[core engine and default templates for kontrol-db]\
data class [SelectQueryBuilder](index.md)(columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnAndValue&gt; = mutableListOf(), includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, predicate: [SqlPredicate](../../-sql-predicate/index.md)? = null, var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [TableAliasBuilder](../../-table-alias-builder/index.md)&lt;[SelectQuery.SelectQueryBuilder](index.md), [SelectQuery](../index.md)&gt;

## Constructors

| | |
|---|---|
| [SelectQueryBuilder](-select-query-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnAndValue&gt; = mutableListOf(), includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, predicate: [SqlPredicate](../../-sql-predicate/index.md)? = null, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | [core engine and default templates for kontrol-db]<br>open override var [alias](alias.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [alias](../../-table-alias-builder/alias.md) | [core engine and default templates for kontrol-db]<br>open fun [alias](../../-table-alias-builder/alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SelectQuery.SelectQueryBuilder](index.md) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [SelectQuery.SelectQueryBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [SelectQuery.SelectQueryBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [SelectQuery](../index.md) |
| [column](column.md) | [core engine and default templates for kontrol-db]<br>fun [column](column.md)(columnName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), expression: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [SelectQuery.SelectQueryBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [SelectQuery.SelectQueryBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [SelectQuery.SelectQueryBuilder](index.md) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.md) | [core engine and default templates for kontrol-db]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SelectQuery.SelectQueryBuilder](index.md)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.md)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SelectQuery.SelectQueryBuilder](index.md) |
| [where](where.md) | [core engine and default templates for kontrol-db]<br>fun [where](where.md)(lambda: [PredicateBuilder](../../-predicate-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [SelectQuery.SelectQueryBuilder](index.md) |

---
title: SelectQueryBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[SelectQuery](../index.html)/[SelectQueryBuilder](index.html)



# SelectQueryBuilder



[core engine and default templates for kontrol-db]\
data class [SelectQueryBuilder](index.html)(columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnAndValue&gt; = mutableListOf(), includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, predicate: [SqlPredicate](../../-sql-predicate/index.html)? = null, var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [TableAliasBuilder](../../-table-alias-builder/index.html)&lt;[SelectQuery.SelectQueryBuilder](index.html), [SelectQuery](../index.html)&gt;



## Constructors


| | |
|---|---|
| [SelectQueryBuilder](-select-query-builder.html) | [core engine and default templates for kontrol-db]<br>constructor(columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnAndValue&gt; = mutableListOf(), includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, predicate: [SqlPredicate](../../-sql-predicate/index.html)? = null, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |


## Properties


| Name | Summary |
|---|---|
| [alias](alias.html) | [core engine and default templates for kontrol-db]<br>open override var [alias](alias.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [alias](../../-table-alias-builder/alias.html) | [core engine and default templates for kontrol-db]<br>open fun [alias](../../-table-alias-builder/alias.html)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SelectQuery.SelectQueryBuilder](index.html) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [SelectQuery.SelectQueryBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [SelectQuery.SelectQueryBuilder](index.html) |
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [SelectQuery](../index.html) |
| [column](column.html) | [core engine and default templates for kontrol-db]<br>fun [column](column.html)(columnName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), expression: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [SelectQuery.SelectQueryBuilder](index.html) |
| [table](../../-table-builder/table.html) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.html)(table: Table): [SelectQuery.SelectQueryBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [SelectQuery.SelectQueryBuilder](index.html) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.html) | [core engine and default templates for kontrol-db]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SelectQuery.SelectQueryBuilder](index.html)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SelectQuery.SelectQueryBuilder](index.html) |
| [where](where.html) | [core engine and default templates for kontrol-db]<br>fun [where](where.html)(lambda: [PredicateBuilder](../../-predicate-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [SelectQuery.SelectQueryBuilder](index.html) |


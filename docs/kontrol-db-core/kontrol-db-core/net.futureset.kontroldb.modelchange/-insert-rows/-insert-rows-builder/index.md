---
title: InsertRowsBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[InsertRows](../index.html)/[InsertRowsBuilder](index.html)



# InsertRowsBuilder



[jvm]\
class [InsertRowsBuilder](index.html)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), fromSelect: [SelectQuery](../../-select-query/index.html)? = null) : [TableAliasBuilder](../../-table-alias-builder/index.html)&lt;[InsertRows.InsertRowsBuilder](index.html), [InsertRows](../index.html)&gt;



## Constructors


| | |
|---|---|
| [InsertRowsBuilder](-insert-rows-builder.html) | [jvm]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), fromSelect: [SelectQuery](../../-select-query/index.html)? = null) |


## Types


| Name | Summary |
|---|---|
| [Companion](-companion/index.html) | [jvm]<br>object [Companion](-companion/index.html) |


## Properties


| Name | Summary |
|---|---|
| [alias](alias.html) | [jvm]<br>open override var [alias](alias.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [addRows](add-rows.html) | [jvm]<br>fun [addRows](add-rows.html)(rows: [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;): [InsertRows.InsertRowsBuilder](index.html) |
| [alias](../../-table-alias-builder/alias.html) | [jvm]<br>open fun [alias](../../-table-alias-builder/alias.html)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertRows.InsertRowsBuilder](index.html) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [InsertRows.InsertRowsBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [InsertRows.InsertRowsBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [InsertRows](../index.html) |
| [fromQuery](from-query.html) | [jvm]<br>fun [fromQuery](from-query.html)(lambda: [SelectQuery.SelectQueryBuilder](../../-select-query/-select-query-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InsertRows.InsertRowsBuilder](index.html) |
| [row](row.html) | [jvm]<br>fun [row](row.html)(lambda: [ValuesBuilder](../../-values-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InsertRows.InsertRowsBuilder](index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [InsertRows.InsertRowsBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [InsertRows.InsertRowsBuilder](index.html) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.html) | [jvm]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertRows.InsertRowsBuilder](index.html)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertRows.InsertRowsBuilder](index.html) |


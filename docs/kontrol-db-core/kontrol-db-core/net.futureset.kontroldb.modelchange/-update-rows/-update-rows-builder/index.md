---
title: UpdateRowsBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[UpdateRows](../index.html)/[UpdateRowsBuilder](index.html)



# UpdateRowsBuilder



[jvm]\
data class [UpdateRowsBuilder](index.html)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, ColumnValue&gt; = mutableMapOf(), predicate: [SqlPredicate](../../-sql-predicate/index.html) = AllOf(emptyList())) : [TableAliasBuilder](../../-table-alias-builder/index.html)&lt;[UpdateRows.UpdateRowsBuilder](index.html), [UpdateRows](../index.html)&gt;



## Constructors


| | |
|---|---|
| [UpdateRowsBuilder](-update-rows-builder.html) | [jvm]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, ColumnValue&gt; = mutableMapOf(), predicate: [SqlPredicate](../../-sql-predicate/index.html) = AllOf(emptyList())) |


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
| [alias](../../-table-alias-builder/alias.html) | [jvm]<br>open fun [alias](../../-table-alias-builder/alias.html)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [UpdateRows.UpdateRowsBuilder](index.html) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [UpdateRows.UpdateRowsBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [UpdateRows.UpdateRowsBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [UpdateRows](../index.html) |
| [set](set.html) | [jvm]<br>fun [set](set.html)(value: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ColumnValue&gt;) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [UpdateRows.UpdateRowsBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [UpdateRows.UpdateRowsBuilder](index.html) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.html) | [jvm]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [UpdateRows.UpdateRowsBuilder](index.html)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [UpdateRows.UpdateRowsBuilder](index.html) |
| [where](where.html) | [jvm]<br>fun [where](where.html)(lambda: [PredicateBuilder](../../-predicate-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [UpdateRows.UpdateRowsBuilder](index.html) |


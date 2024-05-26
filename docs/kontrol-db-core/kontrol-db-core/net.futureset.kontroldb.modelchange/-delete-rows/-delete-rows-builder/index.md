---
title: DeleteRowsBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[DeleteRows](../index.html)/[DeleteRowsBuilder](index.html)



# DeleteRowsBuilder



[jvm]\
class [DeleteRowsBuilder](index.html)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var predicate: [SqlPredicate](../../-sql-predicate/index.html) = AllOf(emptyList())) : [TableAliasBuilder](../../-table-alias-builder/index.html)&lt;[DeleteRows.DeleteRowsBuilder](index.html), [DeleteRows](../index.html)&gt;



## Constructors


| | |
|---|---|
| [DeleteRowsBuilder](-delete-rows-builder.html) | [jvm]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, predicate: [SqlPredicate](../../-sql-predicate/index.html) = AllOf(emptyList())) |


## Properties


| Name | Summary |
|---|---|
| [alias](alias.html) | [jvm]<br>open override var [alias](alias.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [predicate](predicate.html) | [jvm]<br>var [predicate](predicate.html): [SqlPredicate](../../-sql-predicate/index.html) |
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [alias](../../-table-alias-builder/alias.html) | [jvm]<br>open fun [alias](../../-table-alias-builder/alias.html)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DeleteRows.DeleteRowsBuilder](index.html) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [DeleteRows.DeleteRowsBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [DeleteRows.DeleteRowsBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [DeleteRows](../index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [DeleteRows.DeleteRowsBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DeleteRows.DeleteRowsBuilder](index.html) |
| [tableWithAlias](../../-table-alias-builder/table-with-alias.html) | [jvm]<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DeleteRows.DeleteRowsBuilder](index.html)<br>open fun [tableWithAlias](../../-table-alias-builder/table-with-alias.html)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DeleteRows.DeleteRowsBuilder](index.html) |
| [where](where.html) | [jvm]<br>fun [where](where.html)(lambda: [PredicateBuilder](../../-predicate-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [DeleteRows.DeleteRowsBuilder](index.html) |


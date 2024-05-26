---
title: TableAliasBuilder
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[TableAliasBuilder](index.html)



# TableAliasBuilder

interface [TableAliasBuilder](index.html)&lt;[B](index.html) : [TableBuilder](../-table-builder/index.html)&lt;[B](index.html), [T](index.html)&gt;, [T](index.html) : [ModelChange](../-model-change/index.html)&gt; : [TableBuilder](../-table-builder/index.html)&lt;[B](index.html), [T](index.html)&gt; 

#### Inheritors


| |
|---|
| [DeleteRowsBuilder](../-delete-rows/-delete-rows-builder/index.html) |
| [InsertRowsBuilder](../-insert-rows/-insert-rows-builder/index.html) |
| [SelectQueryBuilder](../-select-query/-select-query-builder/index.html) |
| [UpdateRowsBuilder](../-update-rows/-update-rows-builder/index.html) |


## Properties


| Name | Summary |
|---|---|
| [alias](alias.html) | [jvm]<br>abstract var [alias](alias.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](../-table-builder/table.html) | [jvm]<br>abstract var [table](../-table-builder/table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [alias](alias.html) | [jvm]<br>open fun [alias](alias.html)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [B](index.html) |
| [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.html)(): [B](index.html) |
| [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.html)(): [B](index.html) |
| [build](../-table-builder/index.html#2028528719%2FFunctions%2F1904592438) | [jvm]<br>abstract fun [build](../-table-builder/index.html#2028528719%2FFunctions%2F1904592438)(): [T](index.html) |
| [table](../-table-builder/table.html) | [jvm]<br>open fun [table](../-table-builder/table.html)(table: Table): [B](index.html)<br>open fun [table](../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [B](index.html) |
| [tableWithAlias](table-with-alias.html) | [jvm]<br>open fun [tableWithAlias](table-with-alias.html)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [B](index.html)<br>open fun [tableWithAlias](table-with-alias.html)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [B](index.html) |


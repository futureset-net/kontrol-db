---
title: InsertOrUpdateRowBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[InsertOrUpdateRow](../index.html)/[InsertOrUpdateRowBuilder](index.html)



# InsertOrUpdateRowBuilder



[jvm]\
class [InsertOrUpdateRowBuilder](index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), primaryKeys: [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;DbIdentifier&gt; = mutableSetOf(), updateMode: [UpdateMode](../../-update-mode/index.html) = UpdateMode.UPDATE_AND_INSERT) : [TableBuilder](../../-table-builder/index.html)&lt;[InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html), [InsertOrUpdateRow](../index.html)&gt;



## Constructors


| | |
|---|---|
| [InsertOrUpdateRowBuilder](-insert-or-update-row-builder.html) | [jvm]<br>constructor(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), primaryKeys: [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;DbIdentifier&gt; = mutableSetOf(), updateMode: [UpdateMode](../../-update-mode/index.html) = UpdateMode.UPDATE_AND_INSERT) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>open override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [addRows](add-rows.html) | [jvm]<br>fun [addRows](add-rows.html)(rows: [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [InsertOrUpdateRow](../index.html) |
| [mode](mode.html) | [jvm]<br>fun [mode](mode.html)(mode: [UpdateMode](../../-update-mode/index.html)): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |
| [primaryKey](primary-key.html) | [jvm]<br>fun [primaryKey](primary-key.html)(vararg column: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |
| [row](row.html) | [jvm]<br>fun [row](row.html)(lambda: [ValuesBuilder](../../-values-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.html) |


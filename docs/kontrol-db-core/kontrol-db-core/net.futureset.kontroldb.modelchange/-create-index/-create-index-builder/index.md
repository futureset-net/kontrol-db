---
title: CreateIndexBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[CreateIndex](../index.html)/[CreateIndexBuilder](index.html)



# CreateIndexBuilder



[core engine and default templates for kontrol-db]\
class [CreateIndexBuilder](index.html)(indexName: DbIdentifier? = null, tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : [TableBuilder](../../-table-builder/index.html)&lt;[CreateIndex.CreateIndexBuilder](index.html), [CreateIndex](../index.html)&gt;



## Constructors


| | |
|---|---|
| [CreateIndexBuilder](-create-index-builder.html) | [core engine and default templates for kontrol-db]<br>constructor(indexName: DbIdentifier? = null, tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [CreateIndex.CreateIndexBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [CreateIndex.CreateIndexBuilder](index.html) |
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [CreateIndex](../index.html) |
| [column](column.html) | [core engine and default templates for kontrol-db]<br>fun [column](column.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [table](../../-table-builder/table.html) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.html)(table: Table): [CreateIndex.CreateIndexBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [CreateIndex.CreateIndexBuilder](index.html) |
| [tablespace](tablespace.html) | [core engine and default templates for kontrol-db]<br>fun [tablespace](tablespace.html)(tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CreateIndex.CreateIndexBuilder](index.html) |
| [unique](unique.html) | [core engine and default templates for kontrol-db]<br>fun [unique](unique.html)(unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [CreateIndex.CreateIndexBuilder](index.html) |


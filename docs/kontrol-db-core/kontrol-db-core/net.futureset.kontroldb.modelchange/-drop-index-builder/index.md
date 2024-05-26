---
title: DropIndexBuilder
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DropIndexBuilder](index.html)



# DropIndexBuilder



[core engine and default templates for kontrol-db]\
class [DropIndexBuilder](index.html)(indexName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [TableBuilder](../-table-builder/index.html)&lt;[DropIndexBuilder](index.html), [DropIndex](../-drop-index/index.html)&gt;



## Constructors


| | |
|---|---|
| [DropIndexBuilder](-drop-index-builder.html) | [core engine and default templates for kontrol-db]<br>constructor(indexName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.html)(): [DropIndexBuilder](index.html) |
| [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.html) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.html)(): [DropIndexBuilder](index.html) |
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [DropIndex](../-drop-index/index.html) |
| [ifExists](if-exists.html) | [core engine and default templates for kontrol-db]<br>fun [ifExists](if-exists.html)(): [DropIndexBuilder](index.html) |
| [index](--index--.html) | [core engine and default templates for kontrol-db]<br>fun [index](--index--.html)(block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DropIndexBuilder](index.html) |
| [table](../-table-builder/table.html) | [core engine and default templates for kontrol-db]<br>open fun [table](../-table-builder/table.html)(table: Table): [DropIndexBuilder](index.html)<br>open fun [table](../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DropIndexBuilder](index.html) |


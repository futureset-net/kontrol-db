---
title: TableBuilder
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[TableBuilder](index.html)



# TableBuilder

interface [TableBuilder](index.html)&lt;[B](index.html) : [TableBuilder](index.html)&lt;[B](index.html), [T](index.html)&gt;, [T](index.html) : [ModelChange](../-model-change/index.html)&gt; : Builder&lt;[B](index.html), [T](index.html)&gt; 

#### Inheritors


| |
|---|
| [AddForeignKeyBuilder](../-add-foreign-key/-add-foreign-key-builder/index.html) |
| [AddNotNullBuilder](../-add-not-null/-add-not-null-builder/index.html) |
| [AddPrimaryKeyBuilder](../-add-primary-key/-add-primary-key-builder/index.html) |
| [ApplyDsvToTableBuilder](../-apply-dsv-to-table/-apply-dsv-to-table-builder/index.html) |
| [CreateIndexBuilder](../-create-index/-create-index-builder/index.html) |
| [CreateTableBuilder](../-create-table/-create-table-builder/index.html) |
| [DropIndexBuilder](../-drop-index-builder/index.html) |
| [DropTableBuilder](../-drop-table/-drop-table-builder/index.html) |
| [InsertOrUpdateRowBuilder](../-insert-or-update-row/-insert-or-update-row-builder/index.html) |
| [TableAliasBuilder](../-table-alias-builder/index.html) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>abstract var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](as-global-temporary-table.html)(): [B](index.html) |
| [asLocalTemporaryTable](as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](as-local-temporary-table.html)(): [B](index.html) |
| [build](index.html#2028528719%2FFunctions%2F1904592438) | [jvm]<br>abstract fun [build](index.html#2028528719%2FFunctions%2F1904592438)(): [T](index.html) |
| [table](table.html) | [jvm]<br>open fun [table](table.html)(table: Table): [B](index.html)<br>open fun [table](table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [B](index.html) |


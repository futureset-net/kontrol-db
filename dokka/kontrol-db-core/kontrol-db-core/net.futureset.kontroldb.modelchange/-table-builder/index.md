//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[TableBuilder](index.md)

# TableBuilder

interface [TableBuilder](index.md)&lt;[B](index.md) : [TableBuilder](index.md)&lt;[B](index.md), [T](index.md)&gt;, [T](index.md) : [ModelChange](../-model-change/index.md)&gt; : Builder&lt;[B](index.md), [T](index.md)&gt; 

#### Inheritors

| |
|---|
| [AddForeignKeyBuilder](../-add-foreign-key/-add-foreign-key-builder/index.md) |
| [AddNotNullBuilder](../-add-not-null/-add-not-null-builder/index.md) |
| [AddPrimaryKeyBuilder](../-add-primary-key/-add-primary-key-builder/index.md) |
| [ApplyDsvToTableBuilder](../-apply-dsv-to-table/-apply-dsv-to-table-builder/index.md) |
| [CreateIndexBuilder](../-create-index/-create-index-builder/index.md) |
| [CreateTableBuilder](../-create-table/-create-table-builder/index.md) |
| [DropIndexBuilder](../-drop-index-builder/index.md) |
| [DropTableBuilder](../-drop-table/-drop-table-builder/index.md) |
| [InsertOrUpdateRowBuilder](../-insert-or-update-row/-insert-or-update-row-builder/index.md) |
| [TableAliasBuilder](../-table-alias-builder/index.md) |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>abstract var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](as-global-temporary-table.md)(): [B](index.md) |
| [asLocalTemporaryTable](as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](as-local-temporary-table.md)(): [B](index.md) |
| [build](index.md#2028528719%2FFunctions%2F1815734191) | [core engine and default templates for kontrol-db]<br>abstract fun [build](index.md#2028528719%2FFunctions%2F1815734191)(): [T](index.md) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](table.md)(table: Table): [B](index.md)<br>open fun [table](table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [B](index.md) |

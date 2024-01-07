//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[TableAliasBuilder](index.md)

# TableAliasBuilder

interface [TableAliasBuilder](index.md)&lt;[B](index.md) : [TableBuilder](../-table-builder/index.md)&lt;[B](index.md), [T](index.md)&gt;, [T](index.md) : [ModelChange](../-model-change/index.md)&gt; : [TableBuilder](../-table-builder/index.md)&lt;[B](index.md), [T](index.md)&gt; 

#### Inheritors

| |
|---|
| [DeleteRowsBuilder](../-delete-rows/-delete-rows-builder/index.md) |
| [InsertRowsBuilder](../-insert-rows/-insert-rows-builder/index.md) |
| [SelectQueryBuilder](../-select-query/-select-query-builder/index.md) |
| [UpdateRowsBuilder](../-update-rows/-update-rows-builder/index.md) |

## Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | [core engine and default templates for kontrol-db]<br>abstract var [alias](alias.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>abstract var [table](../-table-builder/table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [alias](alias.md) | [core engine and default templates for kontrol-db]<br>open fun [alias](alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [B](index.md) |
| [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.md)(): [B](index.md) |
| [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.md)(): [B](index.md) |
| [build](../-table-builder/index.md#2028528719%2FFunctions%2F1815734191) | [core engine and default templates for kontrol-db]<br>abstract fun [build](../-table-builder/index.md#2028528719%2FFunctions%2F1815734191)(): [T](index.md) |
| [table](../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../-table-builder/table.md)(table: Table): [B](index.md)<br>open fun [table](../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [B](index.md) |
| [tableWithAlias](table-with-alias.md) | [core engine and default templates for kontrol-db]<br>open fun [tableWithAlias](table-with-alias.md)(table: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [B](index.md)<br>open fun [tableWithAlias](table-with-alias.md)(table: Table, alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [B](index.md) |

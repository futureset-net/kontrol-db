//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[DropIndexBuilder](index.md)

# DropIndexBuilder

[core engine and default templates for kontrol-db]\
class [DropIndexBuilder](index.md)(indexName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [TableBuilder](../-table-builder/index.md)&lt;[DropIndexBuilder](index.md), [DropIndex](../-drop-index/index.md)&gt;

## Constructors

| | |
|---|---|
| [DropIndexBuilder](-drop-index-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(indexName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../-table-builder/as-global-temporary-table.md)(): [DropIndexBuilder](index.md) |
| [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../-table-builder/as-local-temporary-table.md)(): [DropIndexBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [DropIndex](../-drop-index/index.md) |
| [ifExists](if-exists.md) | [core engine and default templates for kontrol-db]<br>fun [ifExists](if-exists.md)(): [DropIndexBuilder](index.md) |
| [index](--index--.md) | [core engine and default templates for kontrol-db]<br>fun [index](--index--.md)(block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DropIndexBuilder](index.md) |
| [table](../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../-table-builder/table.md)(table: Table): [DropIndexBuilder](index.md)<br>open fun [table](../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DropIndexBuilder](index.md) |

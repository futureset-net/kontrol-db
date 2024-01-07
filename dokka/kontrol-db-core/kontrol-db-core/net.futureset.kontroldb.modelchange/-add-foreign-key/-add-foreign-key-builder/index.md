//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[AddForeignKey](../index.md)/[AddForeignKeyBuilder](index.md)

# AddForeignKeyBuilder

[core engine and default templates for kontrol-db]\
data class [AddForeignKeyBuilder](index.md)(constraintName: DbIdentifier? = null, foreignTable: SchemaObject? = null, columnMap: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; = mutableMapOf()) : [TableBuilder](../../-table-builder/index.md)&lt;[AddForeignKey.AddForeignKeyBuilder](index.md), [AddForeignKey](../index.md)&gt;

## Constructors

| | |
|---|---|
| [AddForeignKeyBuilder](-add-foreign-key-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(constraintName: DbIdentifier? = null, foreignTable: SchemaObject? = null, columnMap: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; = mutableMapOf()) |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [AddForeignKey.AddForeignKeyBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [AddForeignKey.AddForeignKeyBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [AddForeignKey](../index.md) |
| [constraintName](constraint-name.md) | [core engine and default templates for kontrol-db]<br>fun [constraintName](constraint-name.md)(constraintName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddForeignKey.AddForeignKeyBuilder](index.md) |
| [foreignTable](foreign-table.md) | [core engine and default templates for kontrol-db]<br>fun [foreignTable](foreign-table.md)(table: SchemaObject): [AddForeignKey.AddForeignKeyBuilder](index.md)<br>fun [foreignTable](foreign-table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}) |
| [referencing](referencing.md) | [core engine and default templates for kontrol-db]<br>fun [referencing](referencing.md)(fromAndTo: [ReferencingColumn](../../-referencing-column/index.md)): [AddForeignKey.AddForeignKeyBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [AddForeignKey.AddForeignKeyBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [AddForeignKey.AddForeignKeyBuilder](index.md) |

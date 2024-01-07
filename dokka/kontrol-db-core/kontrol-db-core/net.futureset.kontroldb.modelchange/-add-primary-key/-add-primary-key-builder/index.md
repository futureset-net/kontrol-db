//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[AddPrimaryKey](../index.md)/[AddPrimaryKeyBuilder](index.md)

# AddPrimaryKeyBuilder

[core engine and default templates for kontrol-db]\
class [AddPrimaryKeyBuilder](index.md)(constraintName: DbIdentifier? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf(), clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [TableBuilder](../../-table-builder/index.md)&lt;[AddPrimaryKey.AddPrimaryKeyBuilder](index.md), [AddPrimaryKey](../index.md)&gt;

## Constructors

| | |
|---|---|
| [AddPrimaryKeyBuilder](-add-primary-key-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(constraintName: DbIdentifier? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf(), clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open lateinit override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [AddPrimaryKey.AddPrimaryKeyBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [AddPrimaryKey.AddPrimaryKeyBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [AddPrimaryKey](../index.md) |
| [column](column.md) | [core engine and default templates for kontrol-db]<br>fun [column](column.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [constraintName](constraint-name.md) | [core engine and default templates for kontrol-db]<br>fun [constraintName](constraint-name.md)(constraintName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddPrimaryKey.AddPrimaryKeyBuilder](index.md) |
| [inline](inline.md) | [core engine and default templates for kontrol-db]<br>fun [inline](inline.md)(): [AddPrimaryKey.AddPrimaryKeyBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [AddPrimaryKey.AddPrimaryKeyBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [AddPrimaryKey.AddPrimaryKeyBuilder](index.md) |

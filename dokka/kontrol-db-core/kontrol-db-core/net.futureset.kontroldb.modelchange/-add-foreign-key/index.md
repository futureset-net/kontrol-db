//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[AddForeignKey](index.md)

# AddForeignKey

[core engine and default templates for kontrol-db]\
data class [AddForeignKey](index.md)(val table: Table?, val foreignTable: SchemaObject, val columnMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, DbIdentifier&gt;, val constraintName: DbIdentifier?) : [ConstraintModelChange](../-constraint-model-change/index.md)

## Constructors

| | |
|---|---|
| [AddForeignKey](-add-foreign-key.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table?, foreignTable: SchemaObject, columnMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, DbIdentifier&gt;, constraintName: DbIdentifier?) |

## Types

| Name | Summary |
|---|---|
| [AddForeignKeyBuilder](-add-foreign-key-builder/index.md) | [core engine and default templates for kontrol-db]<br>data class [AddForeignKeyBuilder](-add-foreign-key-builder/index.md)(constraintName: DbIdentifier? = null, foreignTable: SchemaObject? = null, columnMap: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; = mutableMapOf()) : [TableBuilder](../-table-builder/index.md)&lt;[AddForeignKey.AddForeignKeyBuilder](-add-foreign-key-builder/index.md), [AddForeignKey](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columnMap](column-map.md) | [core engine and default templates for kontrol-db]<br>val [columnMap](column-map.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; |
| [constraintName](constraint-name.md) | [core engine and default templates for kontrol-db]<br>open override val [constraintName](constraint-name.md): DbIdentifier? |
| [foreignTable](foreign-table.md) | [core engine and default templates for kontrol-db]<br>val [foreignTable](foreign-table.md): SchemaObject |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table? |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

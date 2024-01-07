//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[AddPrimaryKey](index.md)

# AddPrimaryKey

[core engine and default templates for kontrol-db]\
data class [AddPrimaryKey](index.md)(val table: Table, val columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, val clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, val inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), var constraintName: DbIdentifier? = null) : [ConstraintModelChange](../-constraint-model-change/index.md)

## Constructors

| | |
|---|---|
| [AddPrimaryKey](-add-primary-key.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), constraintName: DbIdentifier? = null) |

## Types

| Name | Summary |
|---|---|
| [AddPrimaryKeyBuilder](-add-primary-key-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [AddPrimaryKeyBuilder](-add-primary-key-builder/index.md)(constraintName: DbIdentifier? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf(), clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [TableBuilder](../-table-builder/index.md)&lt;[AddPrimaryKey.AddPrimaryKeyBuilder](-add-primary-key-builder/index.md), [AddPrimaryKey](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [clustered](clustered.md) | [core engine and default templates for kontrol-db]<br>val [clustered](clustered.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? |
| [columnReferences](column-references.md) | [core engine and default templates for kontrol-db]<br>val [columnReferences](column-references.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [constraintName](constraint-name.md) | [core engine and default templates for kontrol-db]<br>open override var [constraintName](constraint-name.md): DbIdentifier? |
| [inline](inline.md) | [core engine and default templates for kontrol-db]<br>val [inline](inline.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

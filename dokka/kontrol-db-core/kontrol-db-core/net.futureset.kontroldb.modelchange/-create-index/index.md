//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[CreateIndex](index.md)

# CreateIndex

[core engine and default templates for kontrol-db]\
data class [CreateIndex](index.md)(val table: Table, val columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, val clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val indexName: DbIdentifier? = null, val tablespace: Tablespace? = null) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [CreateIndex](-create-index.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table, columnReferences: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt;, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), indexName: DbIdentifier? = null, tablespace: Tablespace? = null) |

## Types

| Name | Summary |
|---|---|
| [CreateIndexBuilder](-create-index-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [CreateIndexBuilder](-create-index-builder/index.md)(indexName: DbIdentifier? = null, tablespace: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, unique: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : [TableBuilder](../-table-builder/index.md)&lt;[CreateIndex.CreateIndexBuilder](-create-index-builder/index.md), [CreateIndex](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [clustered](clustered.md) | [core engine and default templates for kontrol-db]<br>val [clustered](clustered.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [columnReferences](column-references.md) | [core engine and default templates for kontrol-db]<br>val [columnReferences](column-references.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DbIdentifier&gt; |
| [indexName](index-name.md) | [core engine and default templates for kontrol-db]<br>val [indexName](index-name.md): DbIdentifier? = null |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |
| [tablespace](tablespace.md) | [core engine and default templates for kontrol-db]<br>val [tablespace](tablespace.md): Tablespace? = null |
| [unique](unique.md) | [core engine and default templates for kontrol-db]<br>val [unique](unique.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[UpdateRows](index.md)

# UpdateRows

[core engine and default templates for kontrol-db]\
data class [UpdateRows](index.md)(val table: [TableAlias](../-table-alias/index.md), val columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, val predicate: [SqlPredicate](../-sql-predicate/index.md)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [UpdateRows](-update-rows.md) | [core engine and default templates for kontrol-db]<br>constructor(table: [TableAlias](../-table-alias/index.md), columnValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, predicate: [SqlPredicate](../-sql-predicate/index.md)) |

## Types

| Name | Summary |
|---|---|
| [UpdateRowsBuilder](-update-rows-builder/index.md) | [core engine and default templates for kontrol-db]<br>data class [UpdateRowsBuilder](-update-rows-builder/index.md)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, columnValues: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, ColumnValue&gt; = mutableMapOf(), predicate: [SqlPredicate](../-sql-predicate/index.md) = AllOf(emptyList())) : [TableAliasBuilder](../-table-alias-builder/index.md)&lt;[UpdateRows.UpdateRowsBuilder](-update-rows-builder/index.md), [UpdateRows](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columnValues](column-values.md) | [core engine and default templates for kontrol-db]<br>val [columnValues](column-values.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt; |
| [predicate](predicate.md) | [core engine and default templates for kontrol-db]<br>val [predicate](predicate.md): [SqlPredicate](../-sql-predicate/index.md) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): [TableAlias](../-table-alias/index.md) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

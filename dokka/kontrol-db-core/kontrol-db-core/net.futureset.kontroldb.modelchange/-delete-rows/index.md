//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[DeleteRows](index.md)

# DeleteRows

[core engine and default templates for kontrol-db]\
data class [DeleteRows](index.md)(val table: [TableAlias](../-table-alias/index.md), val predicate: [SqlPredicate](../-sql-predicate/index.md)?) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [DeleteRows](-delete-rows.md) | [core engine and default templates for kontrol-db]<br>constructor(table: [TableAlias](../-table-alias/index.md), predicate: [SqlPredicate](../-sql-predicate/index.md)?) |

## Types

| Name | Summary |
|---|---|
| [DeleteRowsBuilder](-delete-rows-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [DeleteRowsBuilder](-delete-rows-builder/index.md)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var predicate: [SqlPredicate](../-sql-predicate/index.md) = AllOf(emptyList())) : [TableAliasBuilder](../-table-alias-builder/index.md)&lt;[DeleteRows.DeleteRowsBuilder](-delete-rows-builder/index.md), [DeleteRows](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [predicate](predicate.md) | [core engine and default templates for kontrol-db]<br>val [predicate](predicate.md): [SqlPredicate](../-sql-predicate/index.md)? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): [TableAlias](../-table-alias/index.md) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

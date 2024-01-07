//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[SelectQuery](index.md)

# SelectQuery

[core engine and default templates for kontrol-db]\
data class [SelectQuery](index.md)(val columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, val table: [TableAlias](../-table-alias/index.md)?, val includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val predicate: [SqlPredicate](../-sql-predicate/index.md)? = null) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [SelectQuery](-select-query.md) | [core engine and default templates for kontrol-db]<br>constructor(columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, table: [TableAlias](../-table-alias/index.md)?, includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), predicate: [SqlPredicate](../-sql-predicate/index.md)? = null) |

## Types

| Name | Summary |
|---|---|
| [SelectQueryBuilder](-select-query-builder/index.md) | [core engine and default templates for kontrol-db]<br>data class [SelectQueryBuilder](-select-query-builder/index.md)(columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnAndValue&gt; = mutableListOf(), includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, predicate: [SqlPredicate](../-sql-predicate/index.md)? = null, var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [TableAliasBuilder](../-table-alias-builder/index.md)&lt;[SelectQuery.SelectQueryBuilder](-select-query-builder/index.md), [SelectQuery](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [columns](columns.md) | [core engine and default templates for kontrol-db]<br>val [columns](columns.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt; |
| [includeData](include-data.md) | [core engine and default templates for kontrol-db]<br>val [includeData](include-data.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [predicate](predicate.md) | [core engine and default templates for kontrol-db]<br>val [predicate](predicate.md): [SqlPredicate](../-sql-predicate/index.md)? = null |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): [TableAlias](../-table-alias/index.md)? |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

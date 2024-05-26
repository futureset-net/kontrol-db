---
title: SelectQuery
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[SelectQuery](index.html)



# SelectQuery



[jvm]\
data class [SelectQuery](index.html)(val columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, val table: [TableAlias](../-table-alias/index.html)?, val includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val predicate: [SqlPredicate](../-sql-predicate/index.html)? = null) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [SelectQuery](-select-query.html) | [jvm]<br>constructor(columns: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt;, table: [TableAlias](../-table-alias/index.html)?, includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), predicate: [SqlPredicate](../-sql-predicate/index.html)? = null) |


## Types


| Name | Summary |
|---|---|
| [SelectQueryBuilder](-select-query-builder/index.html) | [jvm]<br>data class [SelectQueryBuilder](-select-query-builder/index.html)(columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnAndValue&gt; = mutableListOf(), includeData: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, predicate: [SqlPredicate](../-sql-predicate/index.html)? = null, var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [TableAliasBuilder](../-table-alias-builder/index.html)&lt;[SelectQuery.SelectQueryBuilder](-select-query-builder/index.html), [SelectQuery](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [columns](columns.html) | [jvm]<br>val [columns](columns.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ColumnAndValue&gt; |
| [includeData](include-data.html) | [jvm]<br>val [includeData](include-data.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [predicate](predicate.html) | [jvm]<br>val [predicate](predicate.html): [SqlPredicate](../-sql-predicate/index.html)? = null |
| [table](table.html) | [jvm]<br>val [table](table.html): [TableAlias](../-table-alias/index.html)? |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

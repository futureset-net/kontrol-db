---
title: DeleteRows
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DeleteRows](index.html)



# DeleteRows



[jvm]\
data class [DeleteRows](index.html)(val table: [TableAlias](../-table-alias/index.html), val predicate: [SqlPredicate](../-sql-predicate/index.html)?) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DeleteRows](-delete-rows.html) | [jvm]<br>constructor(table: [TableAlias](../-table-alias/index.html), predicate: [SqlPredicate](../-sql-predicate/index.html)?) |


## Types


| Name | Summary |
|---|---|
| [DeleteRowsBuilder](-delete-rows-builder/index.html) | [jvm]<br>class [DeleteRowsBuilder](-delete-rows-builder/index.html)(var alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var predicate: [SqlPredicate](../-sql-predicate/index.html) = AllOf(emptyList())) : [TableAliasBuilder](../-table-alias-builder/index.html)&lt;[DeleteRows.DeleteRowsBuilder](-delete-rows-builder/index.html), [DeleteRows](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [predicate](predicate.html) | [jvm]<br>val [predicate](predicate.html): [SqlPredicate](../-sql-predicate/index.html)? |
| [table](table.html) | [jvm]<br>val [table](table.html): [TableAlias](../-table-alias/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


---
title: ExportQuery
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[ExportQuery](index.html)



# ExportQuery



[core engine and default templates for kontrol-db]\
data class [ExportQuery](index.html)(val selectQuery: [SelectQuery](../-select-query/index.html), val path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ModelChange](../-model-change/index.html), SupportsResultSetHandler



## Constructors


| | |
|---|---|
| [ExportQuery](-export-query.html) | [core engine and default templates for kontrol-db]<br>constructor(selectQuery: [SelectQuery](../-select-query/index.html), path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [path](path.html) | [core engine and default templates for kontrol-db]<br>val [path](path.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [selectQuery](select-query.html) | [core engine and default templates for kontrol-db]<br>val [selectQuery](select-query.html): [SelectQuery](../-select-query/index.html) |
| [separator](separator.html) | [core engine and default templates for kontrol-db]<br>val [separator](separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [resultSetHandler](result-set-handler.html) | [core engine and default templates for kontrol-db]<br>open override fun [resultSetHandler](result-set-handler.html)(effectiveSettings: EffectiveSettings): ([ResultSet](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? |


//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[ExportQuery](index.md)

# ExportQuery

[core engine and default templates for kontrol-db]\
data class [ExportQuery](index.md)(val selectQuery: [SelectQuery](../-select-query/index.md), val path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ModelChange](../-model-change/index.md), SupportsResultSetHandler

## Constructors

| | |
|---|---|
| [ExportQuery](-export-query.md) | [core engine and default templates for kontrol-db]<br>constructor(selectQuery: [SelectQuery](../-select-query/index.md), path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [path](path.md) | [core engine and default templates for kontrol-db]<br>val [path](path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [selectQuery](select-query.md) | [core engine and default templates for kontrol-db]<br>val [selectQuery](select-query.md): [SelectQuery](../-select-query/index.md) |
| [separator](separator.md) | [core engine and default templates for kontrol-db]<br>val [separator](separator.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [resultSetHandler](result-set-handler.md) | [core engine and default templates for kontrol-db]<br>open override fun [resultSetHandler](result-set-handler.md)(effectiveSettings: EffectiveSettings): ([ResultSet](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? |

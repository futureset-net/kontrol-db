//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[ExportQueryBuilder](index.md)

# ExportQueryBuilder

[core engine and default templates for kontrol-db]\
class [ExportQueryBuilder](index.md) : Builder&lt;[ExportQueryBuilder](index.md), [ExportQuery](../-export-query/index.md)&gt;

## Constructors

| | |
|---|---|
| [ExportQueryBuilder](-export-query-builder.md) | [core engine and default templates for kontrol-db]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [ExportQuery](../-export-query/index.md) |
| [outputFile](output-file.md) | [core engine and default templates for kontrol-db]<br>fun [outputFile](output-file.md)(path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [selectFrom](select-from.md) | [core engine and default templates for kontrol-db]<br>fun [selectFrom](select-from.md)(from: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [SelectQuery.SelectQueryBuilder](../-select-query/-select-query-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ExportQueryBuilder](index.md) |
| [separator](separator.md) | [core engine and default templates for kontrol-db]<br>fun [separator](separator.md)(separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ExportQueryBuilder](index.md) |

//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[AddColumns](../index.md)/[AddColumnsBuilder](index.md)

# AddColumnsBuilder

[core engine and default templates for kontrol-db]\
class [AddColumnsBuilder](index.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf()) : Builder&lt;[AddColumns.AddColumnsBuilder](index.md), [AddColumns](../index.md)&gt;

## Constructors

| | |
|---|---|
| [AddColumnsBuilder](-add-columns-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;ColumnDefinition&gt; = mutableListOf()) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [AddColumns](../index.md) |
| [column](column.md) | [core engine and default templates for kontrol-db]<br>fun [column](column.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: ColumnType, block: ColumnDefinition.ColumnDefinitionBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { }): [AddColumns.AddColumnsBuilder](index.md) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>fun [table](table.md)(lambda: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AddColumns.AddColumnsBuilder](index.md) |

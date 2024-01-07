//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[DropColumns](../index.md)/[DropColumnsBuilder](index.md)

# DropColumnsBuilder

[core engine and default templates for kontrol-db]\
class [DropColumnsBuilder](index.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : Builder&lt;[DropColumns.DropColumnsBuilder](index.md), [DropColumns](../index.md)&gt;

## Constructors

| | |
|---|---|
| [DropColumnsBuilder](-drop-columns-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [DropColumns](../index.md) |
| [column](column.md) | [core engine and default templates for kontrol-db]<br>fun [column](column.md)(vararg columns: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DropColumns.DropColumnsBuilder](index.md) |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>fun [table](table.md)(lambda: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [DropColumns.DropColumnsBuilder](index.md) |

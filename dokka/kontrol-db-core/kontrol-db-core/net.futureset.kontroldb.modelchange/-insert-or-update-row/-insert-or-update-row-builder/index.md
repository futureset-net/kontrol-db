//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[InsertOrUpdateRow](../index.md)/[InsertOrUpdateRowBuilder](index.md)

# InsertOrUpdateRowBuilder

[core engine and default templates for kontrol-db]\
class [InsertOrUpdateRowBuilder](index.md)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), primaryKeys: [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;DbIdentifier&gt; = mutableSetOf(), updateMode: [UpdateMode](../../-update-mode/index.md) = UpdateMode.UPDATE_AND_INSERT) : [TableBuilder](../../-table-builder/index.md)&lt;[InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md), [InsertOrUpdateRow](../index.md)&gt;

## Constructors

| | |
|---|---|
| [InsertOrUpdateRowBuilder](-insert-or-update-row-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columnValues: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt; = mutableListOf(), primaryKeys: [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;DbIdentifier&gt; = mutableSetOf(), updateMode: [UpdateMode](../../-update-mode/index.md) = UpdateMode.UPDATE_AND_INSERT) |

## Properties

| Name | Summary |
|---|---|
| [table](table.md) | [core engine and default templates for kontrol-db]<br>open override var [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [addRows](add-rows.md) | [core engine and default templates for kontrol-db]<br>fun [addRows](add-rows.md)(rows: [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;DbIdentifier, ColumnValue&gt;&gt;): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.md)(): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md) | [core engine and default templates for kontrol-db]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.md)(): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [InsertOrUpdateRow](../index.md) |
| [mode](mode.md) | [core engine and default templates for kontrol-db]<br>fun [mode](mode.md)(mode: [UpdateMode](../../-update-mode/index.md)): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |
| [primaryKey](primary-key.md) | [core engine and default templates for kontrol-db]<br>fun [primaryKey](primary-key.md)(vararg column: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |
| [row](row.md) | [core engine and default templates for kontrol-db]<br>fun [row](row.md)(lambda: [ValuesBuilder](../../-values-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |
| [table](../../-table-builder/table.md) | [core engine and default templates for kontrol-db]<br>open fun [table](../../-table-builder/table.md)(table: Table): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md)<br>open fun [table](../../-table-builder/table.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [InsertOrUpdateRow.InsertOrUpdateRowBuilder](index.md) |

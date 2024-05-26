---
title: DropColumnsBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[DropColumns](../index.html)/[DropColumnsBuilder](index.html)



# DropColumnsBuilder



[core engine and default templates for kontrol-db]\
class [DropColumnsBuilder](index.html)(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) : Builder&lt;[DropColumns.DropColumnsBuilder](index.html), [DropColumns](../index.html)&gt;



## Constructors


| | |
|---|---|
| [DropColumnsBuilder](-drop-columns-builder.html) | [core engine and default templates for kontrol-db]<br>constructor(tableName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf()) |


## Functions


| Name | Summary |
|---|---|
| [build](build.html) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.html)(): [DropColumns](../index.html) |
| [column](column.html) | [core engine and default templates for kontrol-db]<br>fun [column](column.html)(vararg columns: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DropColumns.DropColumnsBuilder](index.html) |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>fun [table](table.html)(lambda: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [DropColumns.DropColumnsBuilder](index.html) |


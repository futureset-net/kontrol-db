---
title: AddPrimaryKeyBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[AddPrimaryKey](../index.html)/[AddPrimaryKeyBuilder](index.html)



# AddPrimaryKeyBuilder



[jvm]\
class [AddPrimaryKeyBuilder](index.html)(constraintName: DbIdentifier? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf(), clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [TableBuilder](../../-table-builder/index.html)&lt;[AddPrimaryKey.AddPrimaryKeyBuilder](index.html), [AddPrimaryKey](../index.html)&gt;



## Constructors


| | |
|---|---|
| [AddPrimaryKeyBuilder](-add-primary-key-builder.html) | [jvm]<br>constructor(constraintName: DbIdentifier? = null, columns: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;DbIdentifier&gt; = mutableListOf(), clustered: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, inline: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [AddPrimaryKey.AddPrimaryKeyBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [AddPrimaryKey.AddPrimaryKeyBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [AddPrimaryKey](../index.html) |
| [column](column.html) | [jvm]<br>fun [column](column.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [constraintName](constraint-name.html) | [jvm]<br>fun [constraintName](constraint-name.html)(constraintName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddPrimaryKey.AddPrimaryKeyBuilder](index.html) |
| [inline](inline.html) | [jvm]<br>fun [inline](inline.html)(): [AddPrimaryKey.AddPrimaryKeyBuilder](index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [AddPrimaryKey.AddPrimaryKeyBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [AddPrimaryKey.AddPrimaryKeyBuilder](index.html) |


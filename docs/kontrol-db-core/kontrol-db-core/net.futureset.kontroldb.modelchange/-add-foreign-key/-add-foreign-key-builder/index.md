---
title: AddForeignKeyBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[AddForeignKey](../index.html)/[AddForeignKeyBuilder](index.html)



# AddForeignKeyBuilder



[jvm]\
data class [AddForeignKeyBuilder](index.html)(constraintName: DbIdentifier? = null, foreignTable: SchemaObject? = null, columnMap: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; = mutableMapOf()) : [TableBuilder](../../-table-builder/index.html)&lt;[AddForeignKey.AddForeignKeyBuilder](index.html), [AddForeignKey](../index.html)&gt;



## Constructors


| | |
|---|---|
| [AddForeignKeyBuilder](-add-foreign-key-builder.html) | [jvm]<br>constructor(constraintName: DbIdentifier? = null, foreignTable: SchemaObject? = null, columnMap: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;DbIdentifier, DbIdentifier&gt; = mutableMapOf()) |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [AddForeignKey.AddForeignKeyBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [AddForeignKey.AddForeignKeyBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [AddForeignKey](../index.html) |
| [constraintName](constraint-name.html) | [jvm]<br>fun [constraintName](constraint-name.html)(constraintName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddForeignKey.AddForeignKeyBuilder](index.html) |
| [foreignTable](foreign-table.html) | [jvm]<br>fun [foreignTable](foreign-table.html)(table: SchemaObject): [AddForeignKey.AddForeignKeyBuilder](index.html)<br>fun [foreignTable](foreign-table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}) |
| [referencing](referencing.html) | [jvm]<br>fun [referencing](referencing.html)(fromAndTo: [ReferencingColumn](../../-referencing-column/index.html)): [AddForeignKey.AddForeignKeyBuilder](index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [AddForeignKey.AddForeignKeyBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [AddForeignKey.AddForeignKeyBuilder](index.html) |


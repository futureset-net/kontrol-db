---
title: DropTableBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[DropTable](../index.html)/[DropTableBuilder](index.html)



# DropTableBuilder



[jvm]\
class [DropTableBuilder](index.html) : [TableBuilder](../../-table-builder/index.html)&lt;[DropTable.DropTableBuilder](index.html), [DropTable](../index.html)&gt;



## Constructors


| | |
|---|---|
| [DropTableBuilder](-drop-table-builder.html) | [jvm]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [table](table.html) | [jvm]<br>open lateinit override var [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html) | [jvm]<br>open fun [asGlobalTemporaryTable](../../-table-builder/as-global-temporary-table.html)(): [DropTable.DropTableBuilder](index.html) |
| [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html) | [jvm]<br>open fun [asLocalTemporaryTable](../../-table-builder/as-local-temporary-table.html)(): [DropTable.DropTableBuilder](index.html) |
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [DropTable](../index.html) |
| [table](../../-table-builder/table.html) | [jvm]<br>open fun [table](../../-table-builder/table.html)(table: Table): [DropTable.DropTableBuilder](index.html)<br>open fun [table](../../-table-builder/table.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DropTable.DropTableBuilder](index.html) |


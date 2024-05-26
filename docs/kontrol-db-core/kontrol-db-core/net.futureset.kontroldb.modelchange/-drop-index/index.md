---
title: DropIndex
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DropIndex](index.html)



# DropIndex



[jvm]\
data class [DropIndex](index.html)(val index: SchemaObject, val table: Table, val ifExists: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DropIndex](-drop-index.html) | [jvm]<br>constructor(index: SchemaObject, table: Table, ifExists: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [ifExists](if-exists.html) | [jvm]<br>val [ifExists](if-exists.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [index](--index--.html) | [jvm]<br>val [index](--index--.html): SchemaObject |
| [table](table.html) | [jvm]<br>val [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


---
title: DropIndex
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DropIndex](index.html)



# DropIndex



[core engine and default templates for kontrol-db]\
data class [DropIndex](index.html)(val index: SchemaObject, val table: Table, val ifExists: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DropIndex](-drop-index.html) | [core engine and default templates for kontrol-db]<br>constructor(index: SchemaObject, table: Table, ifExists: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [ifExists](if-exists.html) | [core engine and default templates for kontrol-db]<br>val [ifExists](if-exists.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [index](--index--.html) | [core engine and default templates for kontrol-db]<br>val [index](--index--.html): SchemaObject |
| [table](table.html) | [core engine and default templates for kontrol-db]<br>val [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


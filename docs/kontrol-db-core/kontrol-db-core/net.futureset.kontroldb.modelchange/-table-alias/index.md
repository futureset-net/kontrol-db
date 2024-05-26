---
title: TableAlias
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[TableAlias](index.html)



# TableAlias



[jvm]\
data class [TableAlias](index.html)(val alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val table: Table) : [ModelChange](../-model-change/index.html), SqlString



## Constructors


| | |
|---|---|
| [TableAlias](-table-alias.html) | [jvm]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, table: Table) |


## Properties


| Name | Summary |
|---|---|
| [alias](alias.html) | [jvm]<br>val [alias](alias.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](table.html) | [jvm]<br>val [table](table.html): Table |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toSql](to-sql.html) | [jvm]<br>open override fun [toSql](to-sql.html)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

---
title: DropIfExists
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[DropIfExists](index.html)



# DropIfExists



[jvm]\
data class [DropIfExists](index.html)(val objectName: SchemaObject, val objectType: DbObjectType) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [DropIfExists](-drop-if-exists.html) | [jvm]<br>constructor(objectName: SchemaObject, objectType: DbObjectType) |


## Properties


| Name | Summary |
|---|---|
| [objectName](object-name.html) | [jvm]<br>val [objectName](object-name.html): SchemaObject |
| [objectType](object-type.html) | [jvm]<br>val [objectType](object-type.html): DbObjectType |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


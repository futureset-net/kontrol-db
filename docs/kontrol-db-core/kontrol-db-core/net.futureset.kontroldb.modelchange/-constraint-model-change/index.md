---
title: ConstraintModelChange
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[ConstraintModelChange](index.html)



# ConstraintModelChange

interface [ConstraintModelChange](index.html) : [ModelChange](../-model-change/index.html)

#### Inheritors


| |
|---|
| [AddForeignKey](../-add-foreign-key/index.html) |
| [AddNotNull](../-add-not-null/index.html) |
| [AddPrimaryKey](../-add-primary-key/index.html) |


## Properties


| Name | Summary |
|---|---|
| [constraintName](constraint-name.html) | [jvm]<br>abstract val [constraintName](constraint-name.html): DbIdentifier? |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


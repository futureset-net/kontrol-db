---
title: CreateRole
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[CreateRole](index.html)



# CreateRole



[jvm]\
data class [CreateRole](index.html)(val roleName: DbIdentifier) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [CreateRole](-create-role.html) | [jvm]<br>constructor(roleName: DbIdentifier) |


## Types


| Name | Summary |
|---|---|
| [CreateRoleBuilder](-create-role-builder/index.html) | [jvm]<br>data class [CreateRoleBuilder](-create-role-builder/index.html)(roleName: DbIdentifier? = null) : Builder&lt;[CreateRole.CreateRoleBuilder](-create-role-builder/index.html), [CreateRole](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [roleName](role-name.html) | [jvm]<br>val [roleName](role-name.html): DbIdentifier |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [jvm]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [jvm]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


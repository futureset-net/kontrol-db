---
title: ChangePermissions
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[ChangePermissions](index.html)



# ChangePermissions



[core engine and default templates for kontrol-db]\
data class [ChangePermissions](index.html)(val grantOrRevoke: [GrantOrRevoke](../-grant-or-revoke/index.html), val grantees: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val targetObject: SchemaObject, val targetObjectType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [ChangePermissions](-change-permissions.html) | [core engine and default templates for kontrol-db]<br>constructor(grantOrRevoke: [GrantOrRevoke](../-grant-or-revoke/index.html), grantees: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, targetObject: SchemaObject, targetObjectType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |


## Types


| Name | Summary |
|---|---|
| [ChangePermissionsBuilder](-change-permissions-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [ChangePermissionsBuilder](-change-permissions-builder/index.html)(grantOrRevoke: [GrantOrRevoke](../-grant-or-revoke/index.html), permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : Builder&lt;[ChangePermissions.ChangePermissionsBuilder](-change-permissions-builder/index.html), [ChangePermissions](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [grantees](grantees.html) | [core engine and default templates for kontrol-db]<br>val [grantees](grantees.html): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [grantOrRevoke](grant-or-revoke.html) | [core engine and default templates for kontrol-db]<br>val [grantOrRevoke](grant-or-revoke.html): [GrantOrRevoke](../-grant-or-revoke/index.html) |
| [permissions](permissions.html) | [core engine and default templates for kontrol-db]<br>val [permissions](permissions.html): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [targetObject](target-object.html) | [core engine and default templates for kontrol-db]<br>val [targetObject](target-object.html): SchemaObject |
| [targetObjectType](target-object-type.html) | [core engine and default templates for kontrol-db]<br>val [targetObjectType](target-object-type.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.html) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


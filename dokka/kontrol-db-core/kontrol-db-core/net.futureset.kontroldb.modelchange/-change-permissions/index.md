//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[ChangePermissions](index.md)

# ChangePermissions

[core engine and default templates for kontrol-db]\
data class [ChangePermissions](index.md)(val grantOrRevoke: [GrantOrRevoke](../-grant-or-revoke/index.md), val grantees: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, val permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val targetObject: SchemaObject, val targetObjectType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [ChangePermissions](-change-permissions.md) | [core engine and default templates for kontrol-db]<br>constructor(grantOrRevoke: [GrantOrRevoke](../-grant-or-revoke/index.md), grantees: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt;, permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, targetObject: SchemaObject, targetObjectType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [ChangePermissionsBuilder](-change-permissions-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [ChangePermissionsBuilder](-change-permissions-builder/index.md)(grantOrRevoke: [GrantOrRevoke](../-grant-or-revoke/index.md), permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : Builder&lt;[ChangePermissions.ChangePermissionsBuilder](-change-permissions-builder/index.md), [ChangePermissions](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [grantees](grantees.md) | [core engine and default templates for kontrol-db]<br>val [grantees](grantees.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DbIdentifier&gt; |
| [grantOrRevoke](grant-or-revoke.md) | [core engine and default templates for kontrol-db]<br>val [grantOrRevoke](grant-or-revoke.md): [GrantOrRevoke](../-grant-or-revoke/index.md) |
| [permissions](permissions.md) | [core engine and default templates for kontrol-db]<br>val [permissions](permissions.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [targetObject](target-object.md) | [core engine and default templates for kontrol-db]<br>val [targetObject](target-object.md): SchemaObject |
| [targetObjectType](target-object-type.md) | [core engine and default templates for kontrol-db]<br>val [targetObjectType](target-object-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

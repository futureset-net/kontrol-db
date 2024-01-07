//[kontrol-db-core](../../../../index.md)/[net.futureset.kontroldb.modelchange](../../index.md)/[ChangePermissions](../index.md)/[ChangePermissionsBuilder](index.md)

# ChangePermissionsBuilder

[core engine and default templates for kontrol-db]\
class [ChangePermissionsBuilder](index.md)(grantOrRevoke: [GrantOrRevoke](../../-grant-or-revoke/index.md), permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : Builder&lt;[ChangePermissions.ChangePermissionsBuilder](index.md), [ChangePermissions](../index.md)&gt;

## Constructors

| | |
|---|---|
| [ChangePermissionsBuilder](-change-permissions-builder.md) | [core engine and default templates for kontrol-db]<br>constructor(grantOrRevoke: [GrantOrRevoke](../../-grant-or-revoke/index.md), permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [core engine and default templates for kontrol-db]<br>open override fun [build](build.md)(): [ChangePermissions](../index.md) |
| [objectType](object-type.md) | [core engine and default templates for kontrol-db]<br>fun [objectType](object-type.md)(objectType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ChangePermissions.ChangePermissionsBuilder](index.md)<br>fun [objectType](object-type.md)(objectType: DbObjectType): [ChangePermissions.ChangePermissionsBuilder](index.md) |
| [on](on.md) | [core engine and default templates for kontrol-db]<br>fun [on](on.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}) |
| [to](to.md) | [core engine and default templates for kontrol-db]<br>fun [to](to.md)(vararg grantees: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ChangePermissions.ChangePermissionsBuilder](index.md) |

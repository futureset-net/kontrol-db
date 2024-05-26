---
title: ChangePermissionsBuilder
---
//[kontrol-db-core](../../../../index.html)/[net.futureset.kontroldb.modelchange](../../index.html)/[ChangePermissions](../index.html)/[ChangePermissionsBuilder](index.html)



# ChangePermissionsBuilder



[jvm]\
class [ChangePermissionsBuilder](index.html)(grantOrRevoke: [GrantOrRevoke](../../-grant-or-revoke/index.html), permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : Builder&lt;[ChangePermissions.ChangePermissionsBuilder](index.html), [ChangePermissions](../index.html)&gt;



## Constructors


| | |
|---|---|
| [ChangePermissionsBuilder](-change-permissions-builder.html) | [jvm]<br>constructor(grantOrRevoke: [GrantOrRevoke](../../-grant-or-revoke/index.html), permissions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) |


## Functions


| Name | Summary |
|---|---|
| [build](build.html) | [jvm]<br>open override fun [build](build.html)(): [ChangePermissions](../index.html) |
| [objectType](object-type.html) | [jvm]<br>fun [objectType](object-type.html)(objectType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ChangePermissions.ChangePermissionsBuilder](index.html)<br>fun [objectType](object-type.html)(objectType: DbObjectType): [ChangePermissions.ChangePermissionsBuilder](index.html) |
| [on](on.html) | [jvm]<br>fun [on](on.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, block: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}) |
| [to](to.html) | [jvm]<br>fun [to](to.html)(vararg grantees: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ChangePermissions.ChangePermissionsBuilder](index.html) |


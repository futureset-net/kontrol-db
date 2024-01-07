//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[CreateRole](index.md)

# CreateRole

[core engine and default templates for kontrol-db]\
data class [CreateRole](index.md)(val roleName: DbIdentifier) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [CreateRole](-create-role.md) | [core engine and default templates for kontrol-db]<br>constructor(roleName: DbIdentifier) |

## Types

| Name | Summary |
|---|---|
| [CreateRoleBuilder](-create-role-builder/index.md) | [core engine and default templates for kontrol-db]<br>data class [CreateRoleBuilder](-create-role-builder/index.md)(roleName: DbIdentifier? = null) : Builder&lt;[CreateRole.CreateRoleBuilder](-create-role-builder/index.md), [CreateRole](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [roleName](role-name.md) | [core engine and default templates for kontrol-db]<br>val [roleName](role-name.md): DbIdentifier |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

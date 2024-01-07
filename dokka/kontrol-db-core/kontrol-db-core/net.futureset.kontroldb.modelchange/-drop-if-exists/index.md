//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[DropIfExists](index.md)

# DropIfExists

[core engine and default templates for kontrol-db]\
data class [DropIfExists](index.md)(val objectName: SchemaObject, val objectType: DbObjectType) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [DropIfExists](-drop-if-exists.md) | [core engine and default templates for kontrol-db]<br>constructor(objectName: SchemaObject, objectType: DbObjectType) |

## Properties

| Name | Summary |
|---|---|
| [objectName](object-name.md) | [core engine and default templates for kontrol-db]<br>val [objectName](object-name.md): SchemaObject |
| [objectType](object-type.md) | [core engine and default templates for kontrol-db]<br>val [objectType](object-type.md): DbObjectType |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[ConstraintModelChange](index.md)

# ConstraintModelChange

interface [ConstraintModelChange](index.md) : [ModelChange](../-model-change/index.md)

#### Inheritors

| |
|---|
| [AddForeignKey](../-add-foreign-key/index.md) |
| [AddNotNull](../-add-not-null/index.md) |
| [AddPrimaryKey](../-add-primary-key/index.md) |

## Properties

| Name | Summary |
|---|---|
| [constraintName](constraint-name.md) | [core engine and default templates for kontrol-db]<br>abstract val [constraintName](constraint-name.md): DbIdentifier? |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

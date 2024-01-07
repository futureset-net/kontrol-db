//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[DropIndex](index.md)

# DropIndex

[core engine and default templates for kontrol-db]\
data class [DropIndex](index.md)(val index: SchemaObject, val table: Table, val ifExists: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [DropIndex](-drop-index.md) | [core engine and default templates for kontrol-db]<br>constructor(index: SchemaObject, table: Table, ifExists: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [ifExists](if-exists.md) | [core engine and default templates for kontrol-db]<br>val [ifExists](if-exists.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [index](--index--.md) | [core engine and default templates for kontrol-db]<br>val [index](--index--.md): SchemaObject |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

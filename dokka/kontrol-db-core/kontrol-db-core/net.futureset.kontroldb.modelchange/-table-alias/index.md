//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[TableAlias](index.md)

# TableAlias

[core engine and default templates for kontrol-db]\
data class [TableAlias](index.md)(val alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val table: Table) : [ModelChange](../-model-change/index.md), SqlString

## Constructors

| | |
|---|---|
| [TableAlias](-table-alias.md) | [core engine and default templates for kontrol-db]<br>constructor(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, table: Table) |

## Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | [core engine and default templates for kontrol-db]<br>val [alias](alias.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toSql](to-sql.md) | [core engine and default templates for kontrol-db]<br>open override fun [toSql](to-sql.md)(effectiveSettings: EffectiveSettings): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

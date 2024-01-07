//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[AddNotNull](index.md)

# AddNotNull

[core engine and default templates for kontrol-db]\
data class [AddNotNull](index.md)(val table: Table?, val column: ColumnDefinition, val constraintName: DbIdentifier?) : [ConstraintModelChange](../-constraint-model-change/index.md)

## Constructors

| | |
|---|---|
| [AddNotNull](-add-not-null.md) | [core engine and default templates for kontrol-db]<br>constructor(table: Table?, column: ColumnDefinition, constraintName: DbIdentifier?) |

## Types

| Name | Summary |
|---|---|
| [AddNotNullBuilder](-add-not-null-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [AddNotNullBuilder](-add-not-null-builder/index.md) : [TableBuilder](../-table-builder/index.md)&lt;[AddNotNull.AddNotNullBuilder](-add-not-null-builder/index.md), [AddNotNull](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [column](column.md) | [core engine and default templates for kontrol-db]<br>val [column](column.md): ColumnDefinition |
| [constraintName](constraint-name.md) | [core engine and default templates for kontrol-db]<br>open override val [constraintName](constraint-name.md): DbIdentifier? |
| [table](table.md) | [core engine and default templates for kontrol-db]<br>val [table](table.md): Table? |

## Functions

| Name | Summary |
|---|---|
| [checksum](../-model-change/checksum.md) | [core engine and default templates for kontrol-db]<br>open fun [checksum](../-model-change/checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[CreateView](index.md)

# CreateView

[core engine and default templates for kontrol-db]\
data class [CreateView](index.md)(val view: SchemaObject, val body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val path: Resource?, val wholeDefinition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [CreateView](-create-view.md) | [core engine and default templates for kontrol-db]<br>constructor(view: SchemaObject, body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, path: Resource?, wholeDefinition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |

## Types

| Name | Summary |
|---|---|
| [CreateViewBuilder](-create-view-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [CreateViewBuilder](-create-view-builder/index.md)(viewName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : Builder&lt;[CreateView.CreateViewBuilder](-create-view-builder/index.md), [CreateView](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [body](body.md) | [core engine and default templates for kontrol-db]<br>val [body](body.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [language](language.md) | [core engine and default templates for kontrol-db]<br>val [language](language.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [path](path.md) | [core engine and default templates for kontrol-db]<br>val [path](path.md): Resource? |
| [view](view.md) | [core engine and default templates for kontrol-db]<br>val [view](view.md): SchemaObject |
| [wholeDefinition](whole-definition.md) | [core engine and default templates for kontrol-db]<br>val [wholeDefinition](whole-definition.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](checksum.md) | [core engine and default templates for kontrol-db]<br>open override fun [checksum](checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

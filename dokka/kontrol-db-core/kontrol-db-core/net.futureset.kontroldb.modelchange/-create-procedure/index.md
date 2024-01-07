//[kontrol-db-core](../../../index.md)/[net.futureset.kontroldb.modelchange](../index.md)/[CreateProcedure](index.md)

# CreateProcedure

[core engine and default templates for kontrol-db]\
data class [CreateProcedure](index.md)(val procedure: SchemaObject, val body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val path: Resource?, val wholeDefinition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [ModelChange](../-model-change/index.md)

## Constructors

| | |
|---|---|
| [CreateProcedure](-create-procedure.md) | [core engine and default templates for kontrol-db]<br>constructor(procedure: SchemaObject, body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, path: Resource?, wholeDefinition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |

## Types

| Name | Summary |
|---|---|
| [CreateProcedureBuilder](-create-procedure-builder/index.md) | [core engine and default templates for kontrol-db]<br>class [CreateProcedureBuilder](-create-procedure-builder/index.md) : Builder&lt;[CreateProcedure.CreateProcedureBuilder](-create-procedure-builder/index.md), [CreateProcedure](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [body](body.md) | [core engine and default templates for kontrol-db]<br>val [body](body.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [language](language.md) | [core engine and default templates for kontrol-db]<br>val [language](language.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [path](path.md) | [core engine and default templates for kontrol-db]<br>val [path](path.md): Resource? |
| [procedure](procedure.md) | [core engine and default templates for kontrol-db]<br>val [procedure](procedure.md): SchemaObject |
| [wholeDefinition](whole-definition.md) | [core engine and default templates for kontrol-db]<br>val [wholeDefinition](whole-definition.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [checksum](checksum.md) | [core engine and default templates for kontrol-db]<br>open override fun [checksum](checksum.md)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.md) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

---
title: CreateView
---
//[kontrol-db-core](../../../index.html)/[net.futureset.kontroldb.modelchange](../index.html)/[CreateView](index.html)



# CreateView



[core engine and default templates for kontrol-db]\
data class [CreateView](index.html)(val view: SchemaObject, val body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val path: Resource?, val wholeDefinition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [ModelChange](../-model-change/index.html)



## Constructors


| | |
|---|---|
| [CreateView](-create-view.html) | [core engine and default templates for kontrol-db]<br>constructor(view: SchemaObject, body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, path: Resource?, wholeDefinition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), language: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |


## Types


| Name | Summary |
|---|---|
| [CreateViewBuilder](-create-view-builder/index.html) | [core engine and default templates for kontrol-db]<br>class [CreateViewBuilder](-create-view-builder/index.html)(viewName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : Builder&lt;[CreateView.CreateViewBuilder](-create-view-builder/index.html), [CreateView](index.html)&gt; |


## Properties


| Name | Summary |
|---|---|
| [body](body.html) | [core engine and default templates for kontrol-db]<br>val [body](body.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [language](language.html) | [core engine and default templates for kontrol-db]<br>val [language](language.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [path](path.html) | [core engine and default templates for kontrol-db]<br>val [path](path.html): Resource? |
| [view](view.html) | [core engine and default templates for kontrol-db]<br>val [view](view.html): SchemaObject |
| [wholeDefinition](whole-definition.html) | [core engine and default templates for kontrol-db]<br>val [wholeDefinition](whole-definition.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [checksum](checksum.html) | [core engine and default templates for kontrol-db]<br>open override fun [checksum](checksum.html)(resourceResolver: ResourceResolver): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getName](../-model-change/get-name.html) | [core engine and default templates for kontrol-db]<br>open fun [getName](../-model-change/get-name.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


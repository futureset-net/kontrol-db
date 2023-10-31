package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.ResourceResolver
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Resource
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class CreateView(
    val view: SchemaObject,
    val body: String?,
    val path: Resource?,
    val wholeDefinition: Boolean,
    val language: String?,
) : ModelChange {

    override fun checksum(resourceResolver: ResourceResolver): Int {
        return super.checksum(resourceResolver) + if (path != null) resourceResolver.resourceHash(path) else 0
    }

    class CreateViewBuilder : Builder<CreateViewBuilder, CreateView> {
        private var view: CreateView = CreateView(view = SchemaObject(name = DbIdentifier("")), body = null, path = null, wholeDefinition = true, language = "SQL")

        fun viewName(viewName: String) = apply {
            view = view.copy(view = view.view.copy(name = DbIdentifier(viewName)))
        }
        fun view(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            view = view.copy(view = SchemaObjectBuilder(view.view).apply(lambda).build())
        }

        fun body(body: String) = apply {
            view = view.copy(body = body)
        }

        fun language(language: String) = apply {
            view = view.copy(language = language)
        }

        fun wholeDefinition(wholeDefinition: Boolean) = apply {
            view = view.copy(wholeDefinition = wholeDefinition)
        }

        fun resource(path: String) = apply {
            view = view.copy(path = Resource.resource(path))
        }

        override fun build(): CreateView {
            require((view.body != null) xor (view.path != null))
            return view
        }
    }
}

fun ModelChangesBuilder.createView(lambda: CreateView.CreateViewBuilder.() -> Unit) = apply {
    changes.add(CreateView.CreateViewBuilder().apply(lambda).build())
}

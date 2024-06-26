package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.ResourceResolver
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.Resource
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class CreateProcedure(
    val procedure: SchemaObject,
    val body: String?,
    val path: Resource?,
    val wholeDefinition: Boolean,
    val language: String?,
) : ModelChange {

    override fun checksum(resourceResolver: ResourceResolver): Int {
        return super.checksum(resourceResolver) + if (path != null) resourceResolver.resourceHash(path) else 0
    }

    class CreateProcedureBuilder : Builder<CreateProcedureBuilder, CreateProcedure> {
        private lateinit var procedure: SchemaObject
        private var body: String? = null
        private var resource: Resource? = null
        private var wholeDefinition: Boolean = false
        private var language: String? = null

        fun procedure(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            procedure = SchemaObjectBuilder().apply(lambda).build()
        }

        fun body(body: String) = apply {
            this.body = body
        }

        fun language(language: String) = apply {
            this.language = language
        }

        fun wholeDefinition(wholeDefinition: Boolean) = apply {
            this.wholeDefinition = wholeDefinition
        }

        fun resource(path: String) = apply {
            this.resource = Resource.resource(path)
        }

        override fun build(): CreateProcedure {
            require((body != null) xor (resource != null))
            return CreateProcedure(procedure, body, resource, wholeDefinition, language)
        }
    }
}

/**
 * Create a stored procedure
 *
 * @param name of the procedure
 * @param lambda configure the procedure
 * @return [CreateProcedure]
 * @receiver [ModelChangesBuilder] container for a collection of changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createProcedure
 */
fun ModelChangesBuilder.createProcedure(name: String, lambda: CreateProcedure.CreateProcedureBuilder.() -> Unit) =
    CreateProcedure.CreateProcedureBuilder().procedure { name(name) }.apply(lambda).build().also(changes::add)

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class DropIfExists(val objectName: SchemaObject, val objectType: DbObjectType, val ifExists: Boolean) : ModelChange

class DropIfExistsBuilder : Builder<DropIfExistsBuilder, DropIfExists> {

    private lateinit var objectName: SchemaObject
    private lateinit var objectType: DbObjectType
    private var ifExists: Boolean = false

    fun objectName(name: String, lambda: SchemaObjectBuilder.() -> Unit) = apply {
        objectName = SchemaObjectBuilder().name(name).apply(lambda).build()
    }

    fun objectType(objectType: DbObjectType) = apply {
        this.objectType = objectType
    }

    fun ifExists() = apply {
        ifExists = true
    }

    override fun build(): DropIfExists = DropIfExists(objectName, objectType, ifExists)
}

/**
 * Drop procedure if exists
 *
 * @param name procedure to drop
 * @param lambda configure other procedure options e.g. schema
 * @receiver [ModelChangesBuilder] collection of changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createProcedure
 */
fun ModelChangesBuilder.dropProcedureIfExists(name: String, lambda: SchemaObjectBuilder.() -> Unit = {}) {
    changes.add(DropIfExistsBuilder().objectName(name, lambda).objectType(DbObjectType.PROCEDURE).ifExists().build())
}

fun ModelChangesBuilder.dropTableIfExists(name: String, lambda: SchemaObjectBuilder.() -> Unit = {}) {
    changes.add(DropIfExistsBuilder().objectName(name, lambda).objectType(DbObjectType.TABLE).ifExists().build())
}

fun ModelChangesBuilder.dropViewIfExists(name: String, lambda: SchemaObjectBuilder.() -> Unit = {}) {
    changes.add(DropIfExistsBuilder().objectName(name, lambda).objectType(DbObjectType.VIEW).ifExists().build())
}

/**
 * Drop sequence if exists
 *
 * @param name the name of the sequence
 * @param lambda sequence attributes
 * @receiver [ModelChangesBuilder] container for a set of changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createSequence
 */
fun ModelChangesBuilder.dropSequenceIfExists(name: String, lambda: SchemaObjectBuilder.() -> Unit = {}) {
    changes.add(DropIfExistsBuilder().objectName(name, lambda).objectType(DbObjectType.SEQUENCE).ifExists().build())
}

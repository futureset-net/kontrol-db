package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class DropIfExists(
    val objectName: SchemaObject,
    val objectType: DbObjectType,
    val ifExists: Boolean,
) : ModelChange

class DropIfExistsBuilder : Builder<DropIfExistsBuilder, DropIfExists> {
    private lateinit var objectName: SchemaObject
    private lateinit var objectType: DbObjectType
    private var ifExists: Boolean = false

    fun objectName(
        name: String,
        lambda: SchemaObjectBuilder.() -> Unit,
    ) = apply {
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

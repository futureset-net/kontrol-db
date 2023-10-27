package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbObjectType
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder

data class DropIfExists(val objectName: SchemaObject, val objectType: DbObjectType) : ModelChange

class DropIfExistsBuilder : Builder<DropIfExistsBuilder, DropIfExists> {

    private lateinit var objectName: SchemaObject
    private lateinit var objectType: DbObjectType

    fun objectName(lambda: SchemaObjectBuilder.() -> Unit) = apply {
        objectName = SchemaObjectBuilder().apply(lambda).build()
    }

    fun objectType(objectType: DbObjectType) = apply {
        this.objectType = objectType
    }

    override fun build(): DropIfExists = DropIfExists(objectName, objectType)
}

fun ModelChangesBuilder.dropProcedureIfExists(lambda: SchemaObjectBuilder.() -> Unit) {
    changes.add(DropIfExistsBuilder().objectName(lambda).objectType(DbObjectType.PROCEDURE).build())
}

fun ModelChangesBuilder.dropTableIfExists(lambda: SchemaObjectBuilder.() -> Unit) {
    changes.add(DropIfExistsBuilder().objectName(lambda).objectType(DbObjectType.TABLE).build())
}

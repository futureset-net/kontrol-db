package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder
import net.futureset.kontroldb.model.Table

data class DropIndex(val index: SchemaObject, val table: Table, val ifExists: Boolean) : ModelChange

class DropIndexBuilder : TableBuilder<DropIndexBuilder, DropIndex> {
    override lateinit var table: Table
    private lateinit var index: SchemaObject
    private var ifExists: Boolean = false

    fun ifExists() = apply {
        ifExists = true
    }

    fun index(indexName: String, block: SchemaObjectBuilder.() -> Unit = {}) = apply {
        index = SchemaObjectBuilder(SchemaObject(name = DbIdentifier(indexName))).apply(block).build()
    }

    override fun build(): DropIndex =
        DropIndex(index, table, ifExists)
}

fun ModelChangesBuilder.dropIndexIfExists(lambda: DropIndexBuilder.() -> Unit) {
    changes.add(DropIndexBuilder().ifExists().apply(lambda).build())
}

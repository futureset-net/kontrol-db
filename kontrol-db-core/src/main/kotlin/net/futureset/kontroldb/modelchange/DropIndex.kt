package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder
import net.futureset.kontroldb.model.Table

data class DropIndex(
    val index: SchemaObject,
    val table: Table,
    val ifExists: Boolean,
) : ModelChange

class DropIndexBuilder(
    indexName: String,
) : TableBuilder<DropIndexBuilder, DropIndex> {
    override lateinit var table: Table
    private var index: SchemaObject = SchemaObject(name = DbIdentifier(indexName))
    private var ifExists: Boolean = false

    fun ifExists() = apply {
        ifExists = true
    }

    fun index(block: SchemaObjectBuilder.() -> Unit = {}) = apply {
        index = SchemaObjectBuilder(index).apply(block).build()
    }

    override fun build(): DropIndex = DropIndex(index, table, ifExists)
}

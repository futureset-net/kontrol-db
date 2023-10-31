@file:Suppress("UNCHECKED_CAST")

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder
import net.futureset.kontroldb.model.Table

interface TableBuilder<B : TableBuilder<B, T>, T : ModelChange> : Builder<B, T> {

    var table: Table

    fun table(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) = apply {
        table = Table(
            schemaObject = SchemaObjectBuilder(
                SchemaObject(name = (name ?: "unspecified").let(::DbIdentifier)),
            ).apply(block).build(),
        )
    } as B

    fun localTemporaryTable(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) = apply {
        table = Table(
            tablePersistence = TablePersistence.TEMPORARY,
            schemaObject = SchemaObjectBuilder(
                SchemaObject(name = (name ?: "unspecified").let(::DbIdentifier)),
            ).apply(block).build(),
        )
    } as B

    fun globalTemporaryTable(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) = apply {
        table = Table(
            tablePersistence = TablePersistence.GLOBAL_TEMPORARY,
            schemaObject = SchemaObjectBuilder(
                SchemaObject(name = (name ?: "unspecified").let(::DbIdentifier)),
            ).apply(block).build(),
        )
    } as B

    fun table(table: Table): B {
        this.table = table
        return this as B
    }
}

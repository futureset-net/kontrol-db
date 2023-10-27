@file:Suppress("UNCHECKED_CAST")

package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.TablePersistence

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

@file:Suppress("UNCHECKED_CAST")

package net.futureset.kontroldb

interface TableBuilder<B : TableBuilder<B, T>, T : ModelChange> : Builder<B, T> {

    var table: SchemaObject

    fun table(block: SchemaObjectBuilder.() -> Unit = {}): B {
        return table(SchemaObjectBuilder().apply(block).build())
    }

    fun table(table: SchemaObject): B {
        this.table = table
        return this as B
    }

    fun table(table: String): B {
        table(SchemaObject(name = DbIdentifier(table)))
        return this as B
    }
}

package net.futureset.kontroldb

interface TableBuilder<T : ModelChange> : Builder<T> {

    var table: SchemaObject?

    fun table(block: SchemaObjectBuilder.() -> Unit = {}): TableBuilder<T> {
        return table(SchemaObjectBuilder().apply(block).build())
    }

    fun table(table: SchemaObject) = apply {
        this.table = table
    }

    fun table(table: String) = apply {
        table(SchemaObject(name = DbIdentifier(table)))
    }
}

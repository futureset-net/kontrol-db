package net.futureset.kontroldb

interface TableBuilder<T : ModelChange> : Builder<T> {

    var table: SchemaObject?

    fun table(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) {
        table = SchemaObjectBuilder().apply { name?.let(::name) }.apply(block).build()
    }

    fun table(table: SchemaObject) = apply {
        this.table = table
    }
}

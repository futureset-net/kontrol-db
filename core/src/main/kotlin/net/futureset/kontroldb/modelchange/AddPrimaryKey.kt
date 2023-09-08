package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import net.futureset.kontroldb.Tablespace

data class AddPrimaryKey(
    val table: SchemaObject,
    val columnReferences: List<DbIdentifier>,
    override var constraintName: DbIdentifier? = null,
    val tablespace: Tablespace? = null,
) : ConstraintModelChange

data class AddPrimaryKeyBuilder(
    private var table: SchemaObject? = null,
    private var constraintName: DbIdentifier? = null,
    private var tablespace: String? = null,
    private val columns: MutableList<DbIdentifier> = mutableListOf(),
) : Builder<AddPrimaryKey> {

    fun table(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) = apply {
        table = SchemaObjectBuilder().apply { name?.run(::name) }.apply(block).build()
    }

    fun table(table: SchemaObject) = apply {
        this.table = table
    }

    fun constraintName(constraintName: String) = apply {
        this.constraintName = DbIdentifier(constraintName)
    }

    fun tablespace(tablespace: String) = apply {
        this.tablespace = tablespace
    }

    fun column(name: String) {
        columns.add(DbIdentifier(name))
    }

    override fun build(): AddPrimaryKey {
        return AddPrimaryKey(
            table = requireNotNull(table),
            constraintName = constraintName,
            tablespace = tablespace?.let(::Tablespace),
            columnReferences = columns,
        )
    }
}

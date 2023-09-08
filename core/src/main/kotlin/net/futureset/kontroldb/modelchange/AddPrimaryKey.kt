package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder
import net.futureset.kontroldb.Tablespace

data class AddPrimaryKey(
    val table: SchemaObject,
    val columnReferences: List<DbIdentifier>,
    override var constraintName: DbIdentifier? = null,
    val tablespace: Tablespace? = null,
) : ConstraintModelChange

data class AddPrimaryKeyBuilder(
    override var table: SchemaObject? = null,
    private var constraintName: DbIdentifier? = null,
    private var tablespace: String? = null,
    private val columns: MutableList<DbIdentifier> = mutableListOf(),
) : TableBuilder<AddPrimaryKey> {

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

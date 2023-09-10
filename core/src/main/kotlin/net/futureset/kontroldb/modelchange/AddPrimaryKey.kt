package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

data class AddPrimaryKey(
    val table: SchemaObject?,
    val columnReferences: List<DbIdentifier>,
    override var constraintName: DbIdentifier? = null,
) : ConstraintModelChange {

    data class AddPrimaryKeyBuilder(
        override var table: SchemaObject? = null,
        private var constraintName: DbIdentifier? = null,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
    ) : TableBuilder<AddPrimaryKey> {

        fun constraintName(constraintName: String) = apply {
            this.constraintName = DbIdentifier(constraintName)
        }

        fun column(name: String) {
            columns.add(DbIdentifier(name))
        }

        override fun build(): AddPrimaryKey {
            return AddPrimaryKey(
                table = table,
                constraintName = constraintName,
                columnReferences = columns,
            )
        }
    }
}

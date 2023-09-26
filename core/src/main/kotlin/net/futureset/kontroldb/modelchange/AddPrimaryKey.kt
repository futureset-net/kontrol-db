package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

data class AddPrimaryKey(
    val table: SchemaObject?,
    val columnReferences: List<DbIdentifier>,
    val clustered: Boolean?,
    override var constraintName: DbIdentifier? = null,
) : ConstraintModelChange {

    data class AddPrimaryKeyBuilder(
        override var table: SchemaObject? = null,
        private var constraintName: DbIdentifier? = null,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
        private var clustered: Boolean? = null,
    ) : TableBuilder<AddPrimaryKeyBuilder, AddPrimaryKey> {

        fun constraintName(constraintName: String) = apply {
            this.constraintName = DbIdentifier(constraintName)
        }

        fun clustered(clustered: Boolean) = apply {
            this.clustered = clustered
        }

        fun column(name: String) {
            columns.add(DbIdentifier(name))
        }

        override fun build(): AddPrimaryKey {
            return AddPrimaryKey(
                table = table,
                constraintName = constraintName,
                clustered = clustered,
                columnReferences = columns,
            )
        }
    }
}

fun ModelChangesBuilder.addPrimaryKey(lambda: AddPrimaryKey.AddPrimaryKeyBuilder.() -> Unit): AddPrimaryKey =
    AddPrimaryKey.AddPrimaryKeyBuilder().apply(lambda).build().apply(changes::add)

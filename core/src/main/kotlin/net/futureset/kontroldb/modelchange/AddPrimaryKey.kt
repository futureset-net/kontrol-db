package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Table
import net.futureset.kontroldb.TableBuilder

data class AddPrimaryKey(
    val table: Table,
    val columnReferences: List<DbIdentifier>,
    val clustered: Boolean?,
    val inline: Boolean,
    override var constraintName: DbIdentifier? = null,
) : ConstraintModelChange {

    @KontrolDbDslMarker
    class AddPrimaryKeyBuilder(
        private var constraintName: DbIdentifier? = null,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
        private var clustered: Boolean? = null,
        private var inline: Boolean = false,
    ) : TableBuilder<AddPrimaryKeyBuilder, AddPrimaryKey> {

        override lateinit var table: Table

        fun constraintName(constraintName: String) = apply {
            this.constraintName = DbIdentifier(constraintName)
        }

        fun inline() = apply {
            this.inline = true
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
                inline = inline,
            )
        }
    }
}

fun ModelChangesBuilder.addPrimaryKey(lambda: AddPrimaryKey.AddPrimaryKeyBuilder.() -> Unit): AddPrimaryKey =
    AddPrimaryKey.AddPrimaryKeyBuilder().apply(lambda).build().apply(changes::add)

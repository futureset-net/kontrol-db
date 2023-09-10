package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import net.futureset.kontroldb.TableBuilder

data class AddForeignKey(
    val table: SchemaObject?,
    val foreignTable: SchemaObject,
    val columnMap: Map<DbIdentifier, DbIdentifier>,
    override var constraintName: DbIdentifier? = null,
) : ConstraintModelChange {

    data class AddForeignKeyBuilder(
        override var table: SchemaObject? = null,
        private var constraintName: DbIdentifier? = null,
        var foreignTable: SchemaObject? = null,
        var columnMap: MutableMap<DbIdentifier, DbIdentifier> = mutableMapOf(),
    ) : TableBuilder<AddForeignKey> {

        fun foreignTable(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) {
            foreignTable = SchemaObjectBuilder().apply { name?.let(::name) }.apply(block).build()
        }

        fun foreignTable(table: SchemaObject) = apply {
            this.foreignTable = table
        }
        fun constraintName(constraintName: String) = apply {
            this.constraintName = DbIdentifier(constraintName)
        }

        fun columnMap(fromColumn: String, toColumn: String) = apply {
            columnMap[DbIdentifier(fromColumn)] = DbIdentifier(toColumn)
        }

        override fun build(): AddForeignKey {
            return AddForeignKey(
                table = table,
                foreignTable = requireNotNull(foreignTable) { "Table not specified for Foreign Key" },
                constraintName = constraintName,
                columnMap = columnMap,
            )
        }
    }
}

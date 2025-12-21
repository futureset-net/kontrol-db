package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder
import net.futureset.kontroldb.model.Table

data class AddForeignKey(
    val table: Table?,
    val foreignTable: SchemaObject,
    val columnMap: Map<DbIdentifier, DbIdentifier>,
    override val constraintName: DbIdentifier?,
) : ConstraintModelChange {
    class AddForeignKeyBuilder : TableBuilder<AddForeignKeyBuilder, AddForeignKey> {
        private var constraintName: DbIdentifier? = null
        private var foreignTable: SchemaObject? = null
        private var columnMap: MutableMap<DbIdentifier, DbIdentifier> = mutableMapOf()
        override lateinit var table: Table

        fun foreignTable(
            name: String? = null,
            block: SchemaObjectBuilder.() -> Unit = {},
        ) {
            foreignTable(SchemaObjectBuilder().apply { name?.let(::name) }.apply(block).build())
        }

        fun foreignTable(table: SchemaObject) = apply {
            this.foreignTable = table
        }

        fun constraintName(constraintName: String) = apply {
            this.constraintName = DbIdentifier(constraintName)
        }

        fun referencing(fromAndTo: ReferencingColumn) = apply {
            columnMap[DbIdentifier(fromAndTo.first)] = DbIdentifier(fromAndTo.second)
        }

        override fun build(): AddForeignKey = AddForeignKey(
            table = table,
            foreignTable = requireNotNull(foreignTable) { "Table not specified for Foreign Key" },
            constraintName = constraintName,
            columnMap = columnMap,
        )
    }
}

typealias ReferencingColumn = Pair<String, String>

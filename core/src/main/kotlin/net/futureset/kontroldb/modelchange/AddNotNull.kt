package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbColumnType
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

data class AddNotNull(
    val table: SchemaObject?,
    val column: ColumnDefinition,
    override val constraintName: DbIdentifier? = null,
) : ConstraintModelChange {

    data class AddNotNullBuilder(
        override var table: SchemaObject? = null,
        private var column: ColumnDefinition? = null,
    ) : TableBuilder<AddNotNull> {

        fun column(name: String, type: DbColumnType, block: ColumnDefinitionBuilder.() -> Unit = { }) = apply {
            column = ColumnDefinitionBuilder(name, type).apply(block).build()
        }

        override fun build(): AddNotNull {
            return AddNotNull(
                table = table,
                column = requireNotNull(column),
            )
        }
    }
}

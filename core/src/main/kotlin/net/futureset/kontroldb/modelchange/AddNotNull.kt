package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.ColumnType
import net.futureset.kontroldb.ConstraintModelChange
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Table
import net.futureset.kontroldb.TableBuilder

data class AddNotNull(
    val table: Table?,
    val column: ColumnDefinition,
    override val constraintName: DbIdentifier?,
) : ConstraintModelChange {

    class AddNotNullBuilder : TableBuilder<AddNotNullBuilder, AddNotNull> {

        private lateinit var column: ColumnDefinition
        override lateinit var table: Table
        fun column(name: String, type: ColumnType, block: ColumnDefinitionBuilder.() -> Unit = { }) = apply {
            column = ColumnDefinitionBuilder(name, type).apply(block).build()
        }

        override fun build(): AddNotNull {
            return AddNotNull(
                table = table,
                column = column,
                constraintName = null,
            )
        }
    }
}

fun ModelChangesBuilder.addNotNull(lambda: AddNotNull.AddNotNullBuilder.() -> Unit): AddNotNull =
    AddNotNull.AddNotNullBuilder().apply(lambda).build().apply(changes::add)

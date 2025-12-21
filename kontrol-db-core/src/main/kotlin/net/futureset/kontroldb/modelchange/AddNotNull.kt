package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.ColumnDefinition
import net.futureset.kontroldb.model.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Table

data class AddNotNull(
    val table: Table?,
    val column: ColumnDefinition,
    override val constraintName: DbIdentifier?,
) : ConstraintModelChange {
    class AddNotNullBuilder : TableBuilder<AddNotNullBuilder, AddNotNull> {
        private lateinit var column: ColumnDefinition
        override lateinit var table: Table

        fun column(
            name: String,
            type: ColumnType,
            block: ColumnDefinitionBuilder.() -> Unit = { },
        ) = apply {
            column = ColumnDefinitionBuilder(name, type).apply(block).build()
        }

        override fun build(): AddNotNull = AddNotNull(
            table = table,
            column = column,
            constraintName = null,
        )
    }
}

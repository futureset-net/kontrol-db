package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnDefinition
import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class AddColumns(
    val table: SchemaObject,
    val columnDefinitions: List<ColumnDefinition>,
) : ModelChange {
    @KontrolDbDslMarker
    class AddColumnsBuilder(
        tableName: String,
        private val columns: MutableList<ColumnDefinition> = mutableListOf(),
    ) : Builder<AddColumnsBuilder, AddColumns> {
        private var table: SchemaObject = SchemaObject(name = DbIdentifier(tableName))

        fun table(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            table = SchemaObjectBuilder(table).apply(lambda).build()
        }

        fun column(
            name: String,
            type: ColumnType,
            block: ColumnDefinition.ColumnDefinitionBuilder.() -> Unit = { },
        ) = apply {
            ColumnDefinition
                .ColumnDefinitionBuilder(name, type)
                .apply(block)
                .build()
                .apply(columns::add)
        }

        override fun build(): AddColumns = AddColumns(
            table = table,
            columnDefinitions = columns,
        )
    }
}

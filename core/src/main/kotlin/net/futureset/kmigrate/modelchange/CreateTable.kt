package net.futureset.kmigrate.modelchange

import net.futureset.kmigrate.Builder
import net.futureset.kmigrate.ColumnDefinition
import net.futureset.kmigrate.ColumnDefinitionBuilder
import net.futureset.kmigrate.DbColumnType
import net.futureset.kmigrate.ModelChange
import net.futureset.kmigrate.SchemaObject
import net.futureset.kmigrate.SchemaObjectBuilder
import net.futureset.kmigrate.Tablespace

data class CreateTable(
    val table: SchemaObject,
    val columnDefinitions: List<ColumnDefinition>,
    val tablespace: Tablespace? = null,
) : ModelChange

class CreateTableBuilder : Builder<CreateTable> {

    private var table: SchemaObject? = null
    private var tablespace: String? = null
    private val columns: MutableList<ColumnDefinition> = mutableListOf()

    fun table(block: SchemaObjectBuilder.() -> Unit) = apply {
        table = SchemaObjectBuilder().apply(block).build()
    }

    fun table(table: SchemaObject) = apply {
        this.table = table
    }

    fun tablespace(tablespace: String) = apply {
        this.tablespace = tablespace
    }

    fun column(name: String, type: DbColumnType, block: ColumnDefinitionBuilder.() -> Unit = { }) {
        columns.add(ColumnDefinitionBuilder(name, type).apply(block).build())
    }

    override fun build(): CreateTable {
        return CreateTable(
            table = requireNotNull(table),
            tablespace = tablespace?.let(::Tablespace),
            columnDefinitions = columns,
        )
    }
}

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinitionBuilder
import net.futureset.kontroldb.DbColumnType
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import net.futureset.kontroldb.Tablespace

data class CreateTable(
    val table: SchemaObject,
    val columnDefinitions: List<ColumnDefinition>,
    val tablespace: Tablespace? = null,
) : ModelChange

data class CreateTableBuilder(
    private var table: SchemaObject? = null,
    private var tablespace: String? = null,
    private val columns: MutableList<ColumnDefinition> = mutableListOf(),
) : Builder<CreateTable> {

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

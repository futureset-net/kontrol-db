package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinitionBuilder
import net.futureset.kontroldb.DbColumnType
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder
import net.futureset.kontroldb.Tablespace

data class CreateTable(
    val table: SchemaObject,
    val columnDefinitions: List<ColumnDefinition>,
    val tablespace: Tablespace? = null,
    val primaryKey: AddPrimaryKey? = null,
) : ModelChange {

    data class CreateTableBuilder(
        override var table: SchemaObject? = null,
        private var tablespace: String? = null,
        private val columns: MutableList<ColumnDefinition> = mutableListOf(),
        private var primaryKey: AddPrimaryKey? = null,
    ) : TableBuilder<CreateTable> {

        fun tablespace(tablespace: String) = apply {
            this.tablespace = tablespace
        }

        fun column(name: String, type: DbColumnType, block: ColumnDefinitionBuilder.() -> Unit = { }) = apply {
            ColumnDefinitionBuilder(name, type).apply(block).build().apply(columns::add)
        }

        fun primaryKey(lambda: AddPrimaryKey.AddPrimaryKeyBuilder.() -> Unit) = apply {
            primaryKey = AddPrimaryKey.AddPrimaryKeyBuilder().apply(lambda).build()
        }

        override fun build(): CreateTable {
            return CreateTable(
                table = requireNotNull(table),
                tablespace = tablespace?.let(::Tablespace),
                columnDefinitions = columns,
                primaryKey = primaryKey,
            )
        }
    }
}

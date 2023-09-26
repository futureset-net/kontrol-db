package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.ColumnType
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder
import net.futureset.kontroldb.Tablespace

data class CreateTable(
    val table: SchemaObject,
    val columnDefinitions: List<ColumnDefinition>,
    val tablespace: Tablespace?,
    val primaryKey: AddPrimaryKey?,
    val fromSelect: SelectQuery?,
) : ModelChange {

    @KontrolDbDslMarker
    data class CreateTableBuilder(
        override var table: SchemaObject? = null,
        private var tablespace: String? = null,
        private val columns: MutableList<ColumnDefinition> = mutableListOf(),
        private var primaryKey: AddPrimaryKey? = null,
        private var fromSelect: SelectQuery? = null,
    ) : TableBuilder<CreateTableBuilder, CreateTable> {

        fun tablespace(tablespace: String) = apply {
            this.tablespace = tablespace
        }

        fun fromQuery(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
            fromSelect = SelectQuery.SelectQueryBuilder().apply(lambda).build()
        }

        fun column(name: String, type: ColumnType, block: ColumnDefinitionBuilder.() -> Unit = { }) = apply {
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
                fromSelect = fromSelect,
                primaryKey = primaryKey,
            )
        }
    }
}

fun ModelChangesBuilder.createTable(lambda: CreateTable.CreateTableBuilder.() -> Unit): CreateTable =
    CreateTable.CreateTableBuilder().apply(lambda).build().apply(changes::add)

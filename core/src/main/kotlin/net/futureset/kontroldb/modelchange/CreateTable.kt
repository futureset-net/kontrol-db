package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.ColumnType
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Table
import net.futureset.kontroldb.TableBuilder
import net.futureset.kontroldb.Tablespace

data class CreateTable(
    val table: Table,
    val columnDefinitions: List<ColumnDefinition>,
    val tablespace: Tablespace?,
    val primaryKey: AddPrimaryKey?,
    val fromSelect: SelectQuery?,
    val preserveRowsOnCommit: Boolean,
) : ModelChange {

    @KontrolDbDslMarker
    data class CreateTableBuilder(
        private var tablespace: String? = null,
        private val columns: MutableList<ColumnDefinition> = mutableListOf(),
        private var primaryKey: AddPrimaryKey? = null,
        private var fromSelect: SelectQuery? = null,
        private var preserveRowsOnCommit: Boolean = true,
    ) : TableBuilder<CreateTableBuilder, CreateTable> {

        override lateinit var table: Table

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
            primaryKey = AddPrimaryKey.AddPrimaryKeyBuilder().inline().table(this.table).apply(lambda).build()
        }

        override fun build(): CreateTable {
            return CreateTable(
                table = table,
                tablespace = tablespace?.let(::Tablespace),
                columnDefinitions = columns,
                fromSelect = fromSelect,
                primaryKey = primaryKey,
                preserveRowsOnCommit = preserveRowsOnCommit,
            )
        }
    }
}

fun ModelChangesBuilder.createTable(lambda: CreateTable.CreateTableBuilder.() -> Unit): CreateTable =
    CreateTable.CreateTableBuilder().apply(lambda).build().apply(changes::add)

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.ColumnType
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

data class CreateTemporaryTable(
    val table: SchemaObject,
    val columnDefinitions: List<ColumnDefinition>,
    val tablePersistence: TablePersistence,
    val preserveRowsOnCommit: Boolean,
    val primaryKey: AddPrimaryKey? = null,
    var fromSelect: SelectQuery? = null,
) : ModelChange {

    class CreateTemporaryTableBuilder(
        private var tablePersistence: TablePersistence = TablePersistence.TEMPORARY,
        private val columns: MutableList<ColumnDefinition> = mutableListOf(),
        private var primaryKey: AddPrimaryKey? = null,
        private var fromSelect: SelectQuery? = null,
        private var preserveRowsOnCommit: Boolean = true,
    ) : TableBuilder<CreateTemporaryTableBuilder, CreateTemporaryTable> {
        override lateinit var table: SchemaObject

        fun tableType(tableType: TablePersistence) = apply {
            this.tablePersistence = tableType
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

        override fun build(): CreateTemporaryTable {
            return CreateTemporaryTable(
                table = table,
                tablePersistence = tablePersistence,
                columnDefinitions = columns,
                fromSelect = fromSelect,
                primaryKey = primaryKey,
                preserveRowsOnCommit = preserveRowsOnCommit,
            )
        }
    }
}

fun ModelChangesBuilder.createTemporaryTable(lambda: CreateTemporaryTable.CreateTemporaryTableBuilder.() -> Unit): CreateTemporaryTable =
    CreateTemporaryTable.CreateTemporaryTableBuilder().apply(lambda).build().apply(changes::add)

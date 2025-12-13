package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Table

data class InsertRows(
    val table: Table,
    val columnValues: List<Map<DbIdentifier, ColumnValue>>,
    val fromSelect: SelectQuery?,
) : ModelChange {
    class InsertRowsBuilder(
        override var alias: String? = null,
        private var columnValues: MutableList<Map<DbIdentifier, ColumnValue>> = mutableListOf(),
        private var fromSelect: SelectQuery? = null,
    ) : TableAliasBuilder<InsertRowsBuilder, InsertRows> {
        override lateinit var table: Table

        fun row(lambda: ValuesBuilder.() -> Unit) = apply {
            columnValues.add(ValuesBuilder().apply(lambda).build())
        }

        fun addRows(rows: Iterable<Map<DbIdentifier, ColumnValue>>) = apply {
            columnValues.addAll(rows)
        }

        override fun build(): InsertRows = InsertRows(table, columnValues = columnValues, fromSelect = fromSelect)

        fun fromQuery(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
            fromSelect = SelectQuery.SelectQueryBuilder().apply(lambda).build()
        }

        companion object {
            fun insertRowsInto(
                table: Table,
                block: InsertRowsBuilder.() -> Unit,
            ): InsertRows = InsertRowsBuilder().apply(block).table(table).build()
        }
    }
}

fun ModelChangesBuilder.insertRowsInto(
    tableName: String,
    block: InsertRows.InsertRowsBuilder.() -> Unit,
): InsertRows = InsertRows
    .InsertRowsBuilder()
    .table(tableName)
    .apply(block)
    .build()
    .apply(changes::add)

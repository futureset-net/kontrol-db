package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnValue
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Table

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

        override fun build(): InsertRows {
            return InsertRows(table, columnValues = columnValues, fromSelect = fromSelect)
        }

        fun fromQuery(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
            fromSelect = SelectQuery.SelectQueryBuilder().apply(lambda).build()
        }

        companion object {
            fun insertRows(block: InsertRowsBuilder.() -> Unit): InsertRows {
                return InsertRowsBuilder().apply(block).build()
            }
        }
    }
}

fun ModelChangesBuilder.insertRows(block: InsertRows.InsertRowsBuilder.() -> Unit): InsertRows =
    InsertRows.InsertRowsBuilder().apply(block).build().apply(changes::add)

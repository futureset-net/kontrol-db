package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnValue
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject

data class Insert(
    val table: SchemaObject,
    val columnValues: List<Map<DbIdentifier, ColumnValue>>,
    val fromSelect: SelectQuery?,

) : ModelChange {

    class InsertBuilder(

        override var alias: String? = null,
        private var columnValues: MutableList<Map<DbIdentifier, ColumnValue>> = mutableListOf(),
        private var fromSelect: SelectQuery? = null,
    ) : TableAliasBuilder<InsertBuilder, Insert> {

        override lateinit var table: SchemaObject
        fun values(lambda: ValuesBuilder.() -> Unit) = apply {
            columnValues.add(ValuesBuilder().apply(lambda).build())
        }

        fun addRows(rows: Iterable<Map<DbIdentifier, ColumnValue>>) = apply {
            columnValues.addAll(rows)
        }

        override fun build(): Insert {
            return Insert(table, columnValues = columnValues, fromSelect = fromSelect)
        }

        fun fromQuery(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
            fromSelect = SelectQuery.SelectQueryBuilder().apply(lambda).build()
        }

        companion object {
            fun insertRow(block: InsertBuilder.() -> Unit): Insert {
                return InsertBuilder().apply(block).build()
            }
        }
    }
}

fun ModelChangesBuilder.insert(block: Insert.InsertBuilder.() -> Unit): Insert =
    Insert.InsertBuilder().apply(block).build().apply(changes::add)

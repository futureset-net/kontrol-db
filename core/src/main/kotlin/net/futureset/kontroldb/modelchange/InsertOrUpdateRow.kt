package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Table

enum class UpdateMode {
    UPDATE,
    INSERT,
    UPDATE_AND_INSERT,
}
data class InsertOrUpdateRow(
    val table: Table,
    val columnValues: List<Map<DbIdentifier, ColumnValue>>,
    val primaryKeys: Set<DbIdentifier>,
    val updateMode: UpdateMode,

) : ModelChange {

    @KontrolDbDslMarker
    class InsertOrUpdateRowBuilder(
        private var columnValues: MutableList<Map<DbIdentifier, ColumnValue>> = mutableListOf(),
        private var primaryKeys: MutableSet<DbIdentifier> = mutableSetOf(),
        private var updateMode: UpdateMode = UpdateMode.UPDATE_AND_INSERT,
    ) : TableBuilder<InsertOrUpdateRowBuilder, InsertOrUpdateRow> {

        override lateinit var table: Table
        fun values(lambda: ValuesBuilder.() -> Unit) = apply {
            columnValues.add(ValuesBuilder().apply(lambda).build())
        }

        fun mode(mode: UpdateMode) = apply {
            this.updateMode = mode
        }
        fun primaryKey(vararg column: String) = apply {
            primaryKeys.addAll(column.map(::DbIdentifier))
        }

        fun addRows(rows: Iterable<Map<DbIdentifier, ColumnValue>>) = apply {
            columnValues.addAll(rows)
        }
        override fun build(): InsertOrUpdateRow {
            return InsertOrUpdateRow(table, columnValues = columnValues, primaryKeys = primaryKeys, updateMode)
        }
    }
}

fun ModelChangesBuilder.insertOrUpdateRow(block: InsertOrUpdateRow.InsertOrUpdateRowBuilder.() -> Unit): InsertOrUpdateRow =
    InsertOrUpdateRow.InsertOrUpdateRowBuilder().apply(block).build().apply(changes::add)

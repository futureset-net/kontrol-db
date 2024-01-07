package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnAndValue
import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Table

data class UpdateRows(
    val table: TableAlias,
    val columnValues: List<ColumnAndValue>,
    val predicate: SqlPredicate,
) : ModelChange {

    data class UpdateRowsBuilder(
        override var alias: String? = null,
        private var columnValues: MutableMap<DbIdentifier, ColumnValue> = mutableMapOf(),
        private var predicate: SqlPredicate = AllOf(emptyList()),
    ) : TableAliasBuilder<UpdateRowsBuilder, UpdateRows> {

        override lateinit var table: Table

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        fun set(value: Pair<String, ColumnValue>) {
            columnValues[DbIdentifier(value.first)] = value.second
        }
        override fun build(): UpdateRows {
            return UpdateRows(
                TableAlias(alias, table),
                columnValues = columnValues.map { ColumnAndValue(it.key, it.value, separator = "=") },
                predicate = predicate,
            )
        }

        companion object {
            fun updateRowsOf(table: Table, block: UpdateRowsBuilder.() -> Unit): UpdateRows {
                return UpdateRowsBuilder().apply(block).table(table).build()
            }
        }
    }
}

fun ModelChangesBuilder.updateRowsOf(tableName: String, block: UpdateRows.UpdateRowsBuilder.() -> Unit): UpdateRows =
    UpdateRows.UpdateRowsBuilder().apply(block).table(tableName).build().apply(changes::add)

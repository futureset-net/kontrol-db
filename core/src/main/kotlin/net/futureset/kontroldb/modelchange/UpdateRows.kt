package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChangesBuilder
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
            fun updateRows(block: UpdateRowsBuilder.() -> Unit): UpdateRows {
                return UpdateRowsBuilder().apply(block).build()
            }
        }
    }
}

fun ModelChangesBuilder.updateRows(block: UpdateRows.UpdateRowsBuilder.() -> Unit): UpdateRows =
    UpdateRows.UpdateRowsBuilder.updateRows(block).apply(changes::add)

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnAndValue
import net.futureset.kontroldb.ColumnValue
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject

data class Update(
    val table: TableAlias,
    val columnValues: List<ColumnAndValue>,
    val predicate: SqlPredicate,
) : ModelChange {

    data class UpdateBuilder(
        override var alias: String? = null,
        private var columnValues: MutableMap<DbIdentifier, ColumnValue> = mutableMapOf(),
        private var predicate: SqlPredicate = AllOf(emptyList()),
    ) : TableAliasBuilder<UpdateBuilder, Update> {

        override lateinit var table: SchemaObject

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        fun set(value: Pair<String, ColumnValue>) {
            columnValues[DbIdentifier(value.first)] = value.second
        }
        override fun build(): Update {
            return Update(
                TableAlias(alias, table),
                columnValues = columnValues.map { ColumnAndValue(it.key, it.value, separator = "=") },
                predicate = predicate,
            )
        }

        companion object {
            fun updateRow(block: UpdateBuilder.() -> Unit): Update {
                return UpdateBuilder().apply(block).build()
            }
        }
    }
}

fun ModelChangesBuilder.update(block: Update.UpdateBuilder.() -> Unit): Update =
    Update.UpdateBuilder.updateRow(block).apply(changes::add)

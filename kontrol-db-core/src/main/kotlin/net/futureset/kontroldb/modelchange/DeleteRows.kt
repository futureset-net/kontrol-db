package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.Table

data class DeleteRows(
    val table: TableAlias,
    val predicate: SqlPredicate?,
) : ModelChange {

    class DeleteRowsBuilder(

        override var alias: String? = null,
        var predicate: SqlPredicate = AllOf(emptyList()),
    ) : TableAliasBuilder<DeleteRowsBuilder, DeleteRows> {

        override lateinit var table: Table

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        override fun build(): DeleteRows {
            return DeleteRows(
                TableAlias(alias, table),
                predicate = predicate,
            )
        }
    }
}

fun ModelChangesBuilder.deleteRowsFrom(tableName: String, block: DeleteRows.DeleteRowsBuilder.() -> Unit): DeleteRows =
    DeleteRows.DeleteRowsBuilder().apply(block).table(tableName).build().apply(changes::add)

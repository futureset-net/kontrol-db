package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Table

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

fun ModelChangesBuilder.deleteRows(block: DeleteRows.DeleteRowsBuilder.() -> Unit): DeleteRows =
    DeleteRows.DeleteRowsBuilder().apply(block).build().apply(changes::add)

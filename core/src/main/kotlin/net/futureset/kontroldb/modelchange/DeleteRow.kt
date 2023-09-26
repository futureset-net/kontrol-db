package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject

data class DeleteRow(
    val table: TableAlias,
    val predicate: SqlPredicate?,
) : ModelChange {

    data class DeleteRowBuilder(
        override var table: SchemaObject? = null,
        override var alias: String? = null,
        var predicate: SqlPredicate = AllOf(emptyList()),
    ) : TableAliasBuilder<DeleteRowBuilder, DeleteRow> {

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        override fun build(): DeleteRow {
            return DeleteRow(
                TableAlias(alias, requireNotNull(table)),
                predicate = predicate,
            )
        }
    }
}

fun ModelChangesBuilder.delete(block: DeleteRow.DeleteRowBuilder.() -> Unit): DeleteRow =
    DeleteRow.DeleteRowBuilder().apply(block).build().apply(changes::add)

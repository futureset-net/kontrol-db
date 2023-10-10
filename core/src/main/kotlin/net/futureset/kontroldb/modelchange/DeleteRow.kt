package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject

data class DeleteRow(
    val table: TableAlias,
    val predicate: SqlPredicate?,
) : ModelChange {

    class DeleteRowBuilder(

        override var alias: String? = null,
        var predicate: SqlPredicate = AllOf(emptyList()),
    ) : TableAliasBuilder<DeleteRowBuilder, DeleteRow> {

        override lateinit var table: SchemaObject

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        override fun build(): DeleteRow {
            return DeleteRow(
                TableAlias(alias, table),
                predicate = predicate,
            )
        }
    }

    override fun isDdl() = false
}

fun ModelChangesBuilder.delete(block: DeleteRow.DeleteRowBuilder.() -> Unit): DeleteRow =
    DeleteRow.DeleteRowBuilder().apply(block).build().apply(changes::add)

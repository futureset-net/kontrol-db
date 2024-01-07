package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class DropColumns(
    val table: SchemaObject,
    val columns: List<DbIdentifier>,
) : ModelChange {

    @KontrolDbDslMarker
    class DropColumnsBuilder(
        tableName: String,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
    ) : Builder<DropColumnsBuilder, DropColumns> {

        private var table: SchemaObject = SchemaObject(DbIdentifier(tableName))

        fun table(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            table = SchemaObjectBuilder(table).apply(lambda).build()
        }

        fun column(vararg columns: String) = apply {
            this.columns.addAll(columns.map(::DbIdentifier))
        }

        override fun build(): DropColumns {
            return DropColumns(table = table, columns = columns)
        }
    }
}

fun ModelChangesBuilder.dropColumnsFrom(tableName: String, lambda: DropColumns.DropColumnsBuilder.() -> Unit): DropColumns =
    DropColumns.DropColumnsBuilder(tableName).apply(lambda).build().apply(changes::add)

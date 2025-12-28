package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

/**
 * Rename a column on a table
 */
data class RenameColumn(
    val table: SchemaObject,
    val oldName: DbIdentifier,
    val newName: DbIdentifier,
) : ModelChange {
    @KontrolDbDslMarker
    class RenameColumnBuilder(
        tableName: String,
    ) : Builder<RenameColumnBuilder, RenameColumn> {
        private var table: SchemaObject = SchemaObject(name = DbIdentifier(tableName))
        private var oldName: DbIdentifier? = null
        private var newName: DbIdentifier? = null

        fun table(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            table = SchemaObjectBuilder(table).apply(lambda).build()
        }

        fun oldName(name: String) = apply { this.oldName = DbIdentifier(name) }
        fun newName(name: String) = apply { this.newName = DbIdentifier(name) }

        override fun build(): RenameColumn = RenameColumn(table = table, oldName = checkNotNull(oldName), newName = checkNotNull(newName))
    }
}

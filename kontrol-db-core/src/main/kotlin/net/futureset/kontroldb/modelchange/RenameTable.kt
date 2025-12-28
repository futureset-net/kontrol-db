package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

/**
 * Rename a table from one schema object to another
 */
data class RenameTable(
    val from: SchemaObject,
    val to: DbIdentifier,
) : ModelChange {
    @KontrolDbDslMarker
    class RenameTableBuilder(
        fromName: String,
        toName: String,
    ) : Builder<RenameTableBuilder, RenameTable> {
        private var from: SchemaObject = SchemaObject(name = DbIdentifier(fromName))
        private var to: DbIdentifier = DbIdentifier(toName)

        fun from(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            from = SchemaObjectBuilder(from).apply(lambda).build()
        }

        fun to(name: String) = apply {
            to = DbIdentifier(name)
        }

        override fun build(): RenameTable = RenameTable(from = from, to = to)
    }
}

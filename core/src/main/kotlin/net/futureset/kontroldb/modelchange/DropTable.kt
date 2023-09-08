package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

data class DropTable(
    val table: SchemaObject,
) : ModelChange

data class DropTableBuilder(
    override var table: SchemaObject? = null,
) : TableBuilder<DropTable> {

    override fun build(): DropTable {
        return DropTable(
            table = requireNotNull(table),
        )
    }
}

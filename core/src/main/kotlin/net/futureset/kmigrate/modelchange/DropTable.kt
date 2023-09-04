package net.futureset.kmigrate.modelchange

import net.futureset.kmigrate.Builder
import net.futureset.kmigrate.ModelChange
import net.futureset.kmigrate.SchemaObject
import net.futureset.kmigrate.SchemaObjectBuilder

data class DropTable(
    val table: SchemaObject,
) : ModelChange

class DropTableBuilder : Builder<DropTable> {

    private var table: SchemaObject? = null
    fun table(block: SchemaObjectBuilder.() -> Unit) = apply {
        table = SchemaObjectBuilder().apply(block).build()
    }

    fun table(table: SchemaObject) = apply {
        this.table = table
    }

    override fun build(): DropTable {
        return DropTable(
            table = requireNotNull(table),
        )
    }
}

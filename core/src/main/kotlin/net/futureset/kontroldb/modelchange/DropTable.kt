package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder

data class DropTable(
    val table: SchemaObject,
) : ModelChange

data class DropTableBuilder(
    private var table: SchemaObject? = null,
) : Builder<DropTable> {

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

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

data class DropTable(
    val table: SchemaObject,
) : ModelChange {

    class DropTableBuilder : TableBuilder<DropTableBuilder, DropTable> {

        override lateinit var table: SchemaObject

        override fun build(): DropTable {
            return DropTable(
                table = table,
            )
        }
    }
}

fun ModelChangesBuilder.dropTable(lambda: (DropTable.DropTableBuilder.() -> Unit)): DropTable =
    DropTable.DropTableBuilder().apply(lambda).build().apply(changes::add)

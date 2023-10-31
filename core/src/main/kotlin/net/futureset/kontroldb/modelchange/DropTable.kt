package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.model.Table

data class DropTable(
    val table: Table,
) : ModelChange {

    class DropTableBuilder : TableBuilder<DropTableBuilder, DropTable> {

        override lateinit var table: Table

        override fun build(): DropTable {
            return DropTable(
                table = table,
            )
        }
    }
}

fun ModelChangesBuilder.dropTable(lambda: (DropTable.DropTableBuilder.() -> Unit)): DropTable =
    DropTable.DropTableBuilder().apply(lambda).build().apply(changes::add)

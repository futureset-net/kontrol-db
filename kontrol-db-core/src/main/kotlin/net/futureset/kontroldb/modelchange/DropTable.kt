package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.Table

data class DropTable(
    val table: Table,
) : ModelChange {
    class DropTableBuilder : TableBuilder<DropTableBuilder, DropTable> {
        override lateinit var table: Table

        override fun build(): DropTable = DropTable(table = table)
    }
}

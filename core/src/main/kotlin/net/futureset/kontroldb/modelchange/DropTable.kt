package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.model.Table

data class DropTable(
    val table: Table,
) : ModelChange {

    class DropTableBuilder : TableBuilder<DropTableBuilder, DropTable> {

        override lateinit var table: Table

        override fun build(): DropTable {
            return DropTable(table = table)
        }
    }
}

fun ModelChangesBuilder.dropTable(
    tableName: String,
    lambda: DropTable.DropTableBuilder.() -> Unit = {},
): DropTable =
    DropTable.DropTableBuilder().table(tableName).apply(lambda).build().apply(changes::add)

fun ModelChangesBuilder.dropTable(
    table: Table,
): DropTable = DropTable.DropTableBuilder().table(table).build().apply(changes::add)

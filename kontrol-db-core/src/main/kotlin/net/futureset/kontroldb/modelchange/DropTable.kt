package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder
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

/**
 * Drop a table
 *
 * @param tableName the name of the table
 * @param lambda other table attributes e.g. schema
 * @receiver [ModelChangesBuilder] container for the changes
 * @return [DropTable] the immutable data needed to drop the table
 *
 * @sample net.futureset.kontroldb.samples.CreateATable
 */
fun ModelChangesBuilder.dropTable(
    tableName: String,
    lambda: DropTable.DropTableBuilder.() -> Unit = {},
): DropTable =
    DropTable.DropTableBuilder().table(tableName).apply(lambda).build().apply(changes::add)

fun ModelChangesBuilder.dropTable(
    table: Table,
): DropTable = DropTable.DropTableBuilder().table(table).build().apply(changes::add)

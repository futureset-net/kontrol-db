package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.CreateTableBuilder
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.modelchange.DropTableBuilder
import net.futureset.kontroldb.modelchange.InsertRow
import net.futureset.kontroldb.modelchange.InsertRowBuilder
import net.futureset.kontroldb.modelchange.UpdateRow
import net.futureset.kontroldb.modelchange.UpdateRowBuilder

data class ModelChangesBuilder(
    private val changes: MutableList<ModelChange> = mutableListOf(),
) : Builder<List<ModelChange>> {

    fun createTable(lambda: CreateTableBuilder.() -> Unit): CreateTable {
        val build = CreateTableBuilder().apply(lambda).build()
        changes.add(build)
        return build
    }

    fun dropTable(lambda: (DropTableBuilder.() -> Unit) = { }): DropTable {
        val builder = DropTableBuilder()
        builder.apply(lambda)
        val build = builder.build()
        changes.add(build)
        return build
    }

    fun insertRow(block: InsertRowBuilder.() -> Unit): InsertRow {
        val insertRow = InsertRowBuilder.insertRow(block)
        changes.add(insertRow)
        return insertRow
    }

    fun updateRow(block: UpdateRowBuilder.() -> Unit): UpdateRow {
        val updateRow = UpdateRowBuilder.updateRow(block)
        changes.add(updateRow)
        return updateRow
    }

    override fun build(): List<ModelChange> {
        return changes.toList()
    }
}

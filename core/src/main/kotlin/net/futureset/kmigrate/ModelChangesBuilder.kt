package net.futureset.kmigrate

import net.futureset.kmigrate.modelchange.CreateTable
import net.futureset.kmigrate.modelchange.CreateTableBuilder
import net.futureset.kmigrate.modelchange.DropTable
import net.futureset.kmigrate.modelchange.DropTableBuilder
import net.futureset.kmigrate.modelchange.InsertRow
import net.futureset.kmigrate.modelchange.InsertRowBuilder
import net.futureset.kmigrate.modelchange.UpdateRow
import net.futureset.kmigrate.modelchange.UpdateRowBuilder

class ModelChangesBuilder : Builder<List<ModelChange>> {

    private val changes: MutableList<ModelChange> = mutableListOf()

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

package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.modelchange.AddPrimaryKeyBuilder
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.CreateTableBuilder
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.modelchange.DropTableBuilder
import net.futureset.kontroldb.modelchange.InsertRow
import net.futureset.kontroldb.modelchange.InsertRowBuilder
import net.futureset.kontroldb.modelchange.Update
import net.futureset.kontroldb.modelchange.UpdateBuilder

data class ModelChangesBuilder(
    private val changes: MutableList<ModelChange> = mutableListOf(),
) : Builder<List<ModelChange>> {

    fun addPrimaryKey(lambda: AddPrimaryKeyBuilder.() -> Unit): AddPrimaryKey =
        AddPrimaryKeyBuilder().apply(lambda).build().apply(changes::add)

    fun createTable(lambda: CreateTableBuilder.() -> Unit): CreateTable =
        CreateTableBuilder().apply(lambda).build().apply(changes::add)
    fun dropTable(lambda: (DropTableBuilder.() -> Unit) = { }): DropTable =
        DropTableBuilder().apply(lambda).build().apply(changes::add)

    fun insertRow(block: InsertRowBuilder.() -> Unit): InsertRow =
        InsertRowBuilder.insertRow(block).apply(changes::add)

    fun update(block: UpdateBuilder.() -> Unit): Update =
        UpdateBuilder.updateRow(block).apply(changes::add)

    override fun build(): List<ModelChange> {
        return changes.toList()
    }
}

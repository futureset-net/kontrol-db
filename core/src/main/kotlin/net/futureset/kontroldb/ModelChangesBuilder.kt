package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.AddForeignKey
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.modelchange.AddNotNull.AddNotNullBuilder
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.modelchange.AddPrimaryKey.AddPrimaryKeyBuilder
import net.futureset.kontroldb.modelchange.CreateIndex
import net.futureset.kontroldb.modelchange.CreateIndex.CreateIndexBuilder
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.CreateTable.CreateTableBuilder
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.modelchange.DropTable.DropTableBuilder
import net.futureset.kontroldb.modelchange.InsertRow
import net.futureset.kontroldb.modelchange.InsertRow.InsertRowBuilder
import net.futureset.kontroldb.modelchange.Update
import net.futureset.kontroldb.modelchange.Update.UpdateBuilder

data class ModelChangesBuilder(
    private val changes: MutableList<ModelChange> = mutableListOf(),
) : Builder<List<ModelChange>> {

    fun addNotNull(lambda: AddNotNullBuilder.() -> Unit): AddNotNull =
        AddNotNullBuilder().apply(lambda).build().apply(changes::add)

    fun addPrimaryKey(lambda: AddPrimaryKeyBuilder.() -> Unit): AddPrimaryKey =
        AddPrimaryKeyBuilder().apply(lambda).build().apply(changes::add)

    fun addForeignKey(lambda: AddForeignKey.AddForeignKeyBuilder.() -> Unit): AddForeignKey =
        AddForeignKey.AddForeignKeyBuilder().apply(lambda).build().apply(changes::add)

    fun createIndex(lambda: CreateIndexBuilder.() -> Unit): CreateIndex =
        CreateIndexBuilder().apply(lambda).build().apply(changes::add)

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

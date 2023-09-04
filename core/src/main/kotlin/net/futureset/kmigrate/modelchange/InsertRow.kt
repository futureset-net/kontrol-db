package net.futureset.kmigrate.modelchange

import net.futureset.kmigrate.Builder
import net.futureset.kmigrate.DbIdentifier
import net.futureset.kmigrate.LiteralValue
import net.futureset.kmigrate.ModelChange
import net.futureset.kmigrate.SchemaObject
import net.futureset.kmigrate.SchemaObjectBuilder
import java.time.LocalDate
import java.time.LocalDateTime

data class InsertRow(
    val table: SchemaObject,
    val columnValues: Map<DbIdentifier, LiteralValue>,

) : ModelChange

class InsertRowBuilder : Builder<InsertRow> {

    private var table: SchemaObject? = null
    private var columnValues = mutableMapOf<DbIdentifier, LiteralValue>()

    fun table(block: SchemaObjectBuilder.() -> Unit) {
        table = SchemaObjectBuilder().apply(block).build()
    }
    fun table(schemaObject: SchemaObject) = apply {
        table = schemaObject
    }
    fun value(colName: String, v: String) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }
    fun valueExpression(colName: String, v: String) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue(v, false)
    }
    fun value(colName: String, v: Number) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun value(colName: String, v: LocalDate) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun value(colName: String, v: LocalDateTime) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }
    override fun build(): InsertRow {
        return InsertRow(requireNotNull(table), columnValues = columnValues.toMap())
    }
    companion object {
        fun insertRow(block: InsertRowBuilder.() -> Unit): InsertRow {
            return InsertRowBuilder().apply(block).build()
        }
    }
}

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.LiteralValue
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import java.time.LocalDate
import java.time.LocalDateTime

data class InsertRow(
    val table: SchemaObject,
    val columnValues: Map<DbIdentifier, LiteralValue>,

) : ModelChange

data class InsertRowBuilder(
    private var table: SchemaObject? = null,
    private var columnValues: MutableMap<DbIdentifier, LiteralValue> = mutableMapOf<DbIdentifier, LiteralValue>(),
) : Builder<InsertRow> {

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

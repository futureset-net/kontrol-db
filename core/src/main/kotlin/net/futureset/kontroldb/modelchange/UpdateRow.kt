package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.LiteralValue
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import java.time.LocalDate
import java.time.LocalDateTime

data class UpdateRow(
    val table: SchemaObject,
    val columnValues: Map<DbIdentifier, LiteralValue>,
    val whereColumnsValues: Map<DbIdentifier, LiteralValue>,
) : ModelChange

data class UpdateRowBuilder(
    private var table: SchemaObject? = null,
    private var columnValues: MutableMap<DbIdentifier, LiteralValue> = mutableMapOf(),
    private var whereValues: MutableMap<DbIdentifier, LiteralValue> = mutableMapOf(),
) : Builder<UpdateRow> {

    fun table(block: SchemaObjectBuilder.() -> Unit) {
        table = SchemaObjectBuilder().apply(block).build()
    }

    fun table(table: SchemaObject) = apply {
        this.table = table
    }

    fun whereValue(colName: String, v: String) = apply {
        whereValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun whereExpression(colName: String, v: String) = apply {
        whereValues[DbIdentifier(colName)] = LiteralValue(v, false)
    }

    fun whereValue(colName: String, v: Number) = apply {
        whereValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun whereValue(colName: String, v: LocalDate) = apply {
        whereValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun whereValue(colName: String, v: LocalDateTime) = apply {
        whereValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun setValue(colName: String, v: String) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun setValueFunction(colName: String, v: String) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue(v, false)
    }

    fun setValue(colName: String, v: Number) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun setValue(colName: String, v: LocalDate) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    fun setValue(colName: String, v: LocalDateTime) = apply {
        columnValues[DbIdentifier(colName)] = LiteralValue.value(v)
    }

    override fun build(): UpdateRow {
        return UpdateRow(
            requireNotNull(table),
            columnValues = columnValues.toMap(),
            whereColumnsValues = whereValues.toMap(),
        )
    }

    companion object {
        fun updateRow(block: UpdateRowBuilder.() -> Unit): UpdateRow {
            return UpdateRowBuilder().apply(block).build()
        }
    }
}

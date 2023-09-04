package net.futureset.kmigrate.modelchange

import net.futureset.kmigrate.Builder
import net.futureset.kmigrate.DbIdentifier
import net.futureset.kmigrate.LiteralValue
import net.futureset.kmigrate.ModelChange
import net.futureset.kmigrate.SchemaObject
import net.futureset.kmigrate.SchemaObjectBuilder
import java.time.LocalDate
import java.time.LocalDateTime

data class UpdateRow(
    val table: SchemaObject,
    val columnValues: Map<DbIdentifier, LiteralValue>,
    val whereColumnsValues: Map<DbIdentifier, LiteralValue>,
) : ModelChange

class UpdateRowBuilder : Builder<UpdateRow> {

    private var table: SchemaObject? = null
    private var columnValues = mutableMapOf<DbIdentifier, LiteralValue>()
    private var whereValues = mutableMapOf<DbIdentifier, LiteralValue>()

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
        return UpdateRow(requireNotNull(table), columnValues = columnValues.toMap(), whereColumnsValues = whereValues.toMap())
    }
    companion object {
        fun updateRow(block: UpdateRowBuilder.() -> Unit): UpdateRow {
            return UpdateRowBuilder().apply(block).build()
        }
    }
}

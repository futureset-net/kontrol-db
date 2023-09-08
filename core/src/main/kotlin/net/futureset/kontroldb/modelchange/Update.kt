package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.LiteralValue
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder
import java.time.LocalDate
import java.time.LocalDateTime

data class Update(
    val table: SchemaObject,
    val columnValues: Map<DbIdentifier, LiteralValue>,
    val whereColumnsValues: Map<DbIdentifier, LiteralValue>,
) : ModelChange

data class UpdateBuilder(
    override var table: SchemaObject? = null,
    private var columnValues: MutableMap<DbIdentifier, LiteralValue> = mutableMapOf(),
    private var whereValues: MutableMap<DbIdentifier, LiteralValue> = mutableMapOf(),
) : TableBuilder<Update> {

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

    override fun build(): Update {
        return Update(
            requireNotNull(table),
            columnValues = columnValues.toMap(),
            whereColumnsValues = whereValues.toMap(),
        )
    }

    companion object {
        fun updateRow(block: UpdateBuilder.() -> Unit): Update {
            return UpdateBuilder().apply(block).build()
        }
    }
}

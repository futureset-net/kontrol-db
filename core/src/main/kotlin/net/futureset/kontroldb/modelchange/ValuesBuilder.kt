package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.ColumnValue
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.KontrolDbDslMarker
import java.time.LocalDate
import java.time.LocalDateTime

@KontrolDbDslMarker
class ValuesBuilder :
    Builder<ValuesBuilder, Map<DbIdentifier, ColumnValue>> {
    private val rowValues: MutableMap<DbIdentifier, ColumnValue> = mutableMapOf()

    fun value(colName: String, v: String) = apply {
        rowValues[DbIdentifier(colName)] = ColumnValue.valueFromString(v)
    }

    fun valueExpression(colName: String, v: String) = apply {
        rowValues[DbIdentifier(colName)] = ColumnValue(v, false)
    }

    fun value(colName: String, v: Number) = apply {
        rowValues[DbIdentifier(colName)] = ColumnValue.valueFromNumber(v)
    }

    fun value(colName: String, v: LocalDate) = apply {
        rowValues[DbIdentifier(colName)] = ColumnValue.valueFromDate(v)
    }

    fun value(colName: String, v: LocalDateTime) = apply {
        rowValues[DbIdentifier(colName)] = ColumnValue.valueFromDateTime(v)
    }
    fun value(colName: String, v: Boolean) = apply {
        rowValues[DbIdentifier(colName)] = ColumnValue.valueFromBoolean(v)
    }

    override fun build(): Map<DbIdentifier, ColumnValue> {
        return rowValues
    }
}

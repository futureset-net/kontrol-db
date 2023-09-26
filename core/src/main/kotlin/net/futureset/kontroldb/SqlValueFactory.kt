package net.futureset.kontroldb

import java.time.LocalDate
import java.time.LocalDateTime

interface SqlValueFactory {

    fun col(name: String) = DbIdentifier(name)
    fun literal(value: String) = ColumnValue.valueFromString(value)
    fun literal(value: Boolean) = ColumnValue.valueFromBoolean(value)
    fun literal(value: LocalDate) = ColumnValue.valueFromDate(value)
    fun literal(value: LocalDateTime) = ColumnValue.valueFromDateTime(value)
    fun literal(value: Number) = ColumnValue.valueFromNumber(value)

    fun expression(value: String) = ColumnValue.expression(value)
}

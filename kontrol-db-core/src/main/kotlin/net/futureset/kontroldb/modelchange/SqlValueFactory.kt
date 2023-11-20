package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.DbIdentifier
import java.time.LocalDate
import java.time.LocalDateTime

interface SqlValueFactory {

    fun col(name: String) = DbIdentifier(name)
    fun String.column() = col(this)
    fun String.expression() = ColumnValue.expression(this)
    fun String.literal() = ColumnValue.value(this)
    fun Number.literal() = ColumnValue.value(this)
    fun Boolean.literal() = ColumnValue.value(this)
    fun LocalDate.literal() = ColumnValue.value(this)
    fun LocalDateTime.literal() = ColumnValue.value(this)
}

package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.Operand
import net.futureset.kontroldb.settings.EffectiveSettings
import java.time.LocalDate
import java.time.LocalDateTime

data class ColumnValue(
    private val value: Any?,
    private val quoted: Boolean,

) : SqlString, Operand {
    companion object {

        fun column(name: String) = DbIdentifier(name)

        fun expression(expr: String) = ColumnValue(expr, false)

        fun valueFromBoolean(value: Boolean): ColumnValue {
            return ColumnValue(value.toString(), false)
        }

        fun valueFromBoolean(value: String): ColumnValue {
            return ColumnValue(value.toString(), false)
        }
        fun valueFromNumber(value: Number): ColumnValue {
            return ColumnValue(value.toString(), false)
        }

        fun nullValue(): ColumnValue {
            return ColumnValue(null, false)
        }

        fun valueFromString(value: String): ColumnValue {
            return ColumnValue(value, true)
        }
        fun valueFromDateTime(value: LocalDateTime): ColumnValue {
            return ColumnValue(value, false)
        }
        fun valueFromDate(value: LocalDate): ColumnValue {
            return ColumnValue(value, false)
        }
    }

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        if (value is LocalDate) {
            return effectiveSettings.literalDate(value)
        } else if (value is LocalDateTime) {
            return effectiveSettings.literalDatetime(value)
        }
        return (value?.toString() ?: "NULL").let { if (quoted) "'$it'" else it }
    }
}

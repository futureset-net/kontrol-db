package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.Operand
import net.futureset.kontroldb.settings.EffectiveSettings
import java.time.LocalDate
import java.time.LocalDateTime

data class ColumnValue(
    private val value: Any?,
    private val quoted: Boolean,

) : SqlString, Operand {

    override fun isNull(): Boolean = value == null

    companion object {

        fun column(name: String) = DbIdentifier(name)

        fun expression(expr: String) = ColumnValue(expr, false)

        fun value(value: Any?): ColumnValue {
            return when (value) {
                "NULL" -> ColumnValue(null, false)
                null -> ColumnValue(null, false)
                is Number -> ColumnValue(value, false)
                is Boolean -> ColumnValue(value, false)
                else -> ColumnValue(value, true)
            }
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

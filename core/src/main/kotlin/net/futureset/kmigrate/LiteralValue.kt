package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings
import java.time.LocalDate
import java.time.LocalDateTime

data class LiteralValue(
    private val value: Any?,
    private val quoted: Boolean,

) : SqlString {
    companion object {

        fun value(value: Boolean): LiteralValue {
            return LiteralValue(value.toString(), false)
        }
        fun value(value: Number): LiteralValue {
            return LiteralValue(value.toString(), false)
        }

        fun nullValue(): LiteralValue {
            return LiteralValue(null, false)
        }

        fun value(value: String): LiteralValue {
            return LiteralValue(value, true)
        }
        fun value(value: LocalDateTime): LiteralValue {
            return LiteralValue(value, false)
        }
        fun value(value: LocalDate): LiteralValue {
            return LiteralValue(value, false)
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

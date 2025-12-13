package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

interface ColumnType : SqlString {
    val name: String get() = javaClass.simpleName

    fun size(): String

    override fun toQuoted(effectiveSettings: EffectiveSettings): String = effectiveSettings.getNativeType(this)
}

sealed class StandardColumnTypes : ColumnType {
    data object INT16 : StandardColumnTypes()

    data object INT32 : StandardColumnTypes()

    data object INT64 : StandardColumnTypes()

    data object BOOLEAN : StandardColumnTypes()

    data object DATE : StandardColumnTypes()

    data object DATETIME : StandardColumnTypes()

    data object LOCALDATETIME : StandardColumnTypes()

    data object LOCALDATE : StandardColumnTypes()

    override fun size(): String = ""

    override fun toQuoted(effectiveSettings: EffectiveSettings): String = effectiveSettings.getNativeType(this)

    data class Decimal(
        private val precision: Int = 0,
        val scale: Int = 0,
    ) : ColumnType {
        override fun size(): String {
            var result = ""
            if (precision > 0) {
                result += "($precision"
                if (scale > 0) {
                    result += ", $scale"
                }
                result += ")"
            }
            return result
        }
    }

    data class Varchar(
        private val length: Long,
    ) : ColumnType {
        override fun size(): String = "($length)"
    }

    data class Char(
        private val length: Long,
    ) : ColumnType {
        override fun size(): String = "($length)"
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

interface ColumnType : SqlString {

    fun getName(): String {
        return javaClass.simpleName
    }

    fun size(): String

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return effectiveSettings.getNativeType(this)
    }
}

enum class StandardColumnTypes :
    ColumnType {
    INT_16,
    INT_32,
    INT_64,
    BOOLEAN,
    DATE,
    DATETIME,
    LOCALDATETIME,
    LOCALDATE,
    ;

    override fun size(): String = ""

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return effectiveSettings.getNativeType(this)
    }

    override fun getName(): String {
        return name
    }

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

    data class Char(private val length: Long) :
        ColumnType {
        override fun size(): String = "($length)"
    }
}

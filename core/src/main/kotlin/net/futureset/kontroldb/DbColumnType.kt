package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

interface DbColumnType : SqlString {
    val length: Long
    val precision: Int
    val scale: Int

    fun getName(): String {
        return javaClass.simpleName
    }

    fun size(): String {
        var result = ""
        if (javaClass.isEnum) {
            result = ""
        } else if (precision > 0) {
            result = "($precision"
            if (scale > 0) {
                result += ",$scale"
            }
            result += ")"
        } else if (length > 0) {
            result = "($length)"
        }
        return result
    }

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return effectiveSettings.getNativeType(this)
    }
}

enum class StandardColumnTypes : DbColumnType {
    INT_16 {
        override val length: Long = 2
        override val precision: Int = Short.MAX_VALUE.toString().length
        override val scale: Int = 0
    },
    INT_32 {
        override val length: Long = 4
        override val precision: Int = Int.MAX_VALUE.toString().length
        override val scale: Int = 0
    },
    INT_64 {
        override val length: Long = 8
        override val precision: Int = Long.MAX_VALUE.toString().length
        override val scale: Int = 0
    },
    BOOLEAN {
        override val length: Long = 1
        override val precision: Int = 1
        override val scale: Int = 0
    },
    DATE {
        override val length: Long = 0
        override val precision: Int = 0
        override val scale: Int = 0
    },
    DATETIME {
        override val length: Long = 0
        override val precision: Int = 0
        override val scale: Int = 0
    },
    LOCALDATETIME {
        override val length: Long = 0
        override val precision: Int = 0
        override val scale: Int = 0
    },
    LOCALDATE {
        override val length: Long = 0
        override val precision: Int = 0
        override val scale: Int = 0
    },
    ;

    abstract override val length: Long
    abstract override val precision: Int
    abstract override val scale: Int
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return effectiveSettings.getNativeType(this)
    }

    override fun getName(): String {
        return name
    }

    data class Decimal(
        override val precision: Int = 0,
        override val scale: Int = 0,
    ) : DbColumnType {
        override val length: Long = (precision + scale).toLong()
    }

    data class Varchar(
        override val length: Long,
        override val precision: Int = 0,
        override val scale: Int = 0,
    ) : DbColumnType

    data class Char(
        override val length: Long,
        override val precision: Int = 0,
        override val scale: Int = 0,
    ) : DbColumnType
}

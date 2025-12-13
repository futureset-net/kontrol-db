package net.futureset.kontroldb.settings

import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.StandardColumnTypes

interface AnsiDialect : DbDialect {
    override fun getNativeType(columnType: ColumnType): String = if (columnType is StandardColumnTypes) {
        when (columnType) {
            StandardColumnTypes.INT16 -> "SMALLINT"
            StandardColumnTypes.INT32 -> "INT"
            StandardColumnTypes.INT64 -> "BIGINT"
            StandardColumnTypes.BOOLEAN -> "BIT"
            StandardColumnTypes.DATE -> "DATE"
            StandardColumnTypes.LOCALDATE -> "DATE"
            StandardColumnTypes.DATETIME -> "DATETIME"
            StandardColumnTypes.LOCALDATETIME -> "DATETIME"
        }
    } else {
        columnType.name.uppercase() + columnType.size()
    }
}

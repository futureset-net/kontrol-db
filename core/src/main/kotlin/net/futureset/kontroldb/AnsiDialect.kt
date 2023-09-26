package net.futureset.kontroldb

import net.futureset.kontroldb.settings.DbDialect

interface AnsiDialect : DbDialect {

    override fun getNativeType(columnType: ColumnType): String {
        return if (columnType is StandardColumnTypes) {
            when (columnType) {
                StandardColumnTypes.INT_16 -> "SMALLINT"
                StandardColumnTypes.INT_32 -> "INT"
                StandardColumnTypes.INT_64 -> "BIGINT"
                StandardColumnTypes.BOOLEAN -> "BIT"
                StandardColumnTypes.DATE -> "DATE"
                StandardColumnTypes.LOCALDATE -> "DATE"
                StandardColumnTypes.DATETIME -> "DATETIME"
                StandardColumnTypes.LOCALDATETIME -> "DATETIME"
            }
        } else {
            columnType.getName().uppercase() + columnType.size()
        }
    }
}

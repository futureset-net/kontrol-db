package net.futureset.kmigrate

import net.futureset.kmigrate.settings.DbDialect

interface AnsiDialect : DbDialect {

    override fun getNativeType(dbColumnType: DbColumnType): String {
        if (dbColumnType is StandardColumnTypes) {
            return when (dbColumnType) {
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
            return dbColumnType.getName().uppercase() + dbColumnType.size()
        }
    }
}

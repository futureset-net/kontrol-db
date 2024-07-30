package net.futureset.kontroldb.oracle.dialect

import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.StandardColumnTypes
import net.futureset.kontroldb.settings.AnsiDialect
import net.futureset.kontroldb.settings.DbDialect
import org.koin.core.annotation.Singleton
import java.time.LocalDate
import java.time.LocalDateTime

@Singleton(binds = [DbDialect::class])
class OracleDialect : AnsiDialect {

    override val supportsTablespace: Boolean = true
    override val supportsCatalogs: Boolean = false

    override val openQuote = "\""
    override val closeQuote = "\""
    override val batchSeparator = "\n/\n"
    override val statementSeparator = "\n\n"
    override val nullableByDefault = false
    override val ddlInTransactions = false
    override val databaseName = "oracle"
    override val literalTrue: String = "1"
    override val literalFalse: String = "0"
    override val order: Int = 10

    override fun changeScript(scriptLines: MutableList<String>, targetTool: String) {
        scriptLines.add(0, "SET ECHO ON")
        scriptLines.add(0, "WHENEVER SQLERROR EXIT SQL.SQLCODE")
        scriptLines.add("EXIT")
    }

    override fun dbNowTimestamp(): String {
        return "CURRENT_TIMESTAMP"
    }

    override fun startTransaction(id: Int): String {
        return "START TRANSACTION"
    }

    override fun endTransaction(id: Int): String {
        return "COMMIT"
    }

    override fun getNativeType(columnType: ColumnType): String {
        return when (columnType) {
            StandardColumnTypes.BOOLEAN -> return "NUMBER(1)"
            StandardColumnTypes.INT64 -> return "NUMBER(19)"
            StandardColumnTypes.INT32 -> return "NUMBER(10)"
            StandardColumnTypes.INT16 -> return "NUMBER(5)"
            StandardColumnTypes.DATE -> return "DATE"
            StandardColumnTypes.LOCALDATETIME -> return "DATE"
            StandardColumnTypes.DATETIME -> return "TIMESTAMP(0) WITH TIME ZONE"
            is StandardColumnTypes.Varchar -> return super.getNativeType(columnType).replace("VARCHAR(", "VARCHAR2(")
            else -> super.getNativeType(columnType)
        }
    }

    override fun literalDatetime(date: LocalDateTime): String {
        return "TIMESTAMP " + super.literalDatetime(date)
    }

    override fun literalDate(date: LocalDate): String {
        return "DATE " + super.literalDate(date)
    }
}

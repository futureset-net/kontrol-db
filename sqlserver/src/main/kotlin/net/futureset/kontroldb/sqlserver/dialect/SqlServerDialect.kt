package net.futureset.kontroldb.sqlserver.dialect

import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.AnsiDialect
import net.futureset.kontroldb.settings.DbDialect
import org.koin.core.annotation.Singleton
import java.nio.file.Path
import java.sql.Connection

@Singleton(binds = [DbDialect::class])
class SqlServerDialect : AnsiDialect {

    override val supportsTablespace: Boolean = false
    override val supportsCatalogs: Boolean = true

    override val openQuote = "["
    override val closeQuote = "]"
    override val batchSeparator = "\ngo\n"
    override val statementSeparator = ";\n\n"
    override val nullableByDefault = true
    override val ddlInTransactions = true
    override val databaseName = "sqlserver"
    override val literalTrue: String = "1"
    override val literalFalse: String = "0"
    override val order: Int = 10
    override fun dbNowTimestamp(): String {
        return "CURRENT_TIMESTAMP"
    }

    override fun startTransaction(id: Int): String {
        return "BEGIN TRAN"
    }

    override fun endTransaction(id: Int): String {
        return "COMMIT TRAN"
    }

    override fun tempTable(table: Table): Table {
        return when (table.tablePersistence) {
            TablePersistence.TEMPORARY -> table.copy(schemaObject = SchemaObject(name = DbIdentifier("#" + (table.schemaObject.name.name.trimStart('#')))))
            TablePersistence.GLOBAL_TEMPORARY -> table.copy(schemaObject = SchemaObject(name = DbIdentifier("##" + (table.schemaObject.name.name.trimStart('#')))))
            else -> table
        }
    }

    override fun runScriptAgainstDb(emptyDb: Connection, sqlScript: Path) {
        TODO("Not yet implemented")
    }
}

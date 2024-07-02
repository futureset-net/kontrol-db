package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorFactory
import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class InsertRowsGenerator(es: EffectiveSettings, private val sqlGeneratorFactory: SqlGeneratorFactory) : DbAwareGenerator<InsertRows>(es, InsertRows::class) {

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "oracle"

    override fun convertSingle(): InsertRows.() -> String? = {
        fromSelect?.let { selectQuery ->
            table.toQuoted { "INSERT INTO $it (" } +
                selectQuery.columns.map { it.columnName }.columnNames() + ") " +
                sqlGeneratorFactory.generateSqlSingle(selectQuery)
        } ?: if (columnValues.size < 2) {
            table.toQuoted { "INSERT INTO $it (" } + columnValues.first().keys.columnNames() + ") VALUES " +
                columnValues.joinToString(
                    separator = "),\n                (",
                    prefix = "(",
                    postfix = ")",
                ) { row -> joinQuotableValues(row.values) }
        } else {
            "INSERT ALL\n" +
                columnValues.joinToString(separator = "\n") { row ->
                    table.toQuoted { "    INTO $it(" } + row.keys.columnNames() + ") VALUES (${joinQuotableValues(row.values)})"
                } + "\nSELECT * FROM DUAL"
        }
    }
}

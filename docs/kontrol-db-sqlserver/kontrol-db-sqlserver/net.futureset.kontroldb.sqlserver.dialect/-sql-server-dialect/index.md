---
title: SqlServerDialect
---
//[kontrol-db-sqlserver](../../../index.html)/[net.futureset.kontroldb.sqlserver.dialect](../index.html)/[SqlServerDialect](index.html)



# SqlServerDialect



[sqlserver extensions for kontrol-db]\
class [SqlServerDialect](index.html) : AnsiDialect



## Constructors


| | |
|---|---|
| [SqlServerDialect](-sql-server-dialect.html) | [sqlserver extensions for kontrol-db]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [batchSeparator](batch-separator.html) | [sqlserver extensions for kontrol-db]<br>open override val [batchSeparator](batch-separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [closeQuote](close-quote.html) | [sqlserver extensions for kontrol-db]<br>open override val [closeQuote](close-quote.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [databaseName](database-name.html) | [sqlserver extensions for kontrol-db]<br>open override val [databaseName](database-name.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ddlInTransactions](ddl-in-transactions.html) | [sqlserver extensions for kontrol-db]<br>open override val [ddlInTransactions](ddl-in-transactions.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [literalFalse](literal-false.html) | [sqlserver extensions for kontrol-db]<br>open override val [literalFalse](literal-false.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalTrue](literal-true.html) | [sqlserver extensions for kontrol-db]<br>open override val [literalTrue](literal-true.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [nullableByDefault](nullable-by-default.html) | [sqlserver extensions for kontrol-db]<br>open override val [nullableByDefault](nullable-by-default.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [openQuote](open-quote.html) | [sqlserver extensions for kontrol-db]<br>open override val [openQuote](open-quote.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [order](order.html) | [sqlserver extensions for kontrol-db]<br>open override val [order](order.html): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 10 |
| [statementSeparator](statement-separator.html) | [sqlserver extensions for kontrol-db]<br>open override val [statementSeparator](statement-separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [supportsCatalogs](supports-catalogs.html) | [sqlserver extensions for kontrol-db]<br>open override val [supportsCatalogs](supports-catalogs.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [supportsTablespace](supports-tablespace.html) | [sqlserver extensions for kontrol-db]<br>open override val [supportsTablespace](supports-tablespace.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |


## Functions


| Name | Summary |
|---|---|
| [compareTo](index.html#84698461%2FFunctions%2F1661855367) | [sqlserver extensions for kontrol-db]<br>open operator override fun [compareTo](index.html#84698461%2FFunctions%2F1661855367)(other: DbDialect): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [connectionProps](index.html#-466960205%2FFunctions%2F1661855367) | [sqlserver extensions for kontrol-db]<br>open fun [connectionProps](index.html#-466960205%2FFunctions%2F1661855367)(): [Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html) |
| [dbNowTimestamp](db-now-timestamp.html) | [sqlserver extensions for kontrol-db]<br>open override fun [dbNowTimestamp](db-now-timestamp.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [endTransaction](end-transaction.html) | [sqlserver extensions for kontrol-db]<br>open override fun [endTransaction](end-transaction.html)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getNativeType](index.html#-1276212093%2FFunctions%2F1661855367) | [sqlserver extensions for kontrol-db]<br>open override fun [getNativeType](index.html#-1276212093%2FFunctions%2F1661855367)(columnType: ColumnType): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalDate](index.html#-326339082%2FFunctions%2F1661855367) | [sqlserver extensions for kontrol-db]<br>open fun [literalDate](index.html#-326339082%2FFunctions%2F1661855367)(date: [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalDatetime](index.html#-598586314%2FFunctions%2F1661855367) | [sqlserver extensions for kontrol-db]<br>open fun [literalDatetime](index.html#-598586314%2FFunctions%2F1661855367)(date: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [quote](index.html#2100218619%2FFunctions%2F1661855367) | [sqlserver extensions for kontrol-db]<br>open fun [quote](index.html#2100218619%2FFunctions%2F1661855367)(str: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [runScriptAgainstDb](run-script-against-db.html) | [sqlserver extensions for kontrol-db]<br>open override fun [runScriptAgainstDb](run-script-against-db.html)(emptyDb: [Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html), sqlScript: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) |
| [startTransaction](start-transaction.html) | [sqlserver extensions for kontrol-db]<br>open override fun [startTransaction](start-transaction.html)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [tempTable](temp-table.html) | [sqlserver extensions for kontrol-db]<br>open override fun [tempTable](temp-table.html)(table: Table): Table |


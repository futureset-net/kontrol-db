---
title: HsqlDbDialect
---
//[kontrol-db-hsqldb](../../../index.html)/[net.futureset.kontroldb.hsqldb.dialect](../index.html)/[HsqlDbDialect](index.html)



# HsqlDbDialect



[hsqldb extensions for kontrol-db]\
class [HsqlDbDialect](index.html) : AnsiDialect



## Constructors


| | |
|---|---|
| [HsqlDbDialect](-hsql-db-dialect.html) | [hsqldb extensions for kontrol-db]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [batchSeparator](batch-separator.html) | [hsqldb extensions for kontrol-db]<br>open override val [batchSeparator](batch-separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [closeQuote](close-quote.html) | [hsqldb extensions for kontrol-db]<br>open override val [closeQuote](close-quote.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [databaseName](database-name.html) | [hsqldb extensions for kontrol-db]<br>open override val [databaseName](database-name.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ddlInTransactions](ddl-in-transactions.html) | [hsqldb extensions for kontrol-db]<br>open override val [ddlInTransactions](ddl-in-transactions.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [literalFalse](literal-false.html) | [hsqldb extensions for kontrol-db]<br>open override val [literalFalse](literal-false.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalTrue](literal-true.html) | [hsqldb extensions for kontrol-db]<br>open override val [literalTrue](literal-true.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [nullableByDefault](nullable-by-default.html) | [hsqldb extensions for kontrol-db]<br>open override val [nullableByDefault](nullable-by-default.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [openQuote](open-quote.html) | [hsqldb extensions for kontrol-db]<br>open override val [openQuote](open-quote.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [order](order.html) | [hsqldb extensions for kontrol-db]<br>open override val [order](order.html): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 10 |
| [statementSeparator](statement-separator.html) | [hsqldb extensions for kontrol-db]<br>open override val [statementSeparator](statement-separator.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [supportsCatalogs](supports-catalogs.html) | [hsqldb extensions for kontrol-db]<br>open override val [supportsCatalogs](supports-catalogs.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [supportsTablespace](supports-tablespace.html) | [hsqldb extensions for kontrol-db]<br>open override val [supportsTablespace](supports-tablespace.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |


## Functions


| Name | Summary |
|---|---|
| [compareTo](index.html#84698461%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open operator override fun [compareTo](index.html#84698461%2FFunctions%2F1975689373)(other: DbDialect): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [connectionProps](index.html#-466960205%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open fun [connectionProps](index.html#-466960205%2FFunctions%2F1975689373)(): [Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html) |
| [dbNowTimestamp](db-now-timestamp.html) | [hsqldb extensions for kontrol-db]<br>open override fun [dbNowTimestamp](db-now-timestamp.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [endTransaction](end-transaction.html) | [hsqldb extensions for kontrol-db]<br>open override fun [endTransaction](end-transaction.html)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getNativeType](index.html#-1276212093%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open override fun [getNativeType](index.html#-1276212093%2FFunctions%2F1975689373)(columnType: ColumnType): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalDate](index.html#-326339082%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open fun [literalDate](index.html#-326339082%2FFunctions%2F1975689373)(date: [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalDatetime](index.html#-598586314%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open fun [literalDatetime](index.html#-598586314%2FFunctions%2F1975689373)(date: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [quote](index.html#2100218619%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open fun [quote](index.html#2100218619%2FFunctions%2F1975689373)(str: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [runScriptAgainstDb](run-script-against-db.html) | [hsqldb extensions for kontrol-db]<br>open override fun [runScriptAgainstDb](run-script-against-db.html)(emptyDb: [Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html), sqlScript: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) |
| [startTransaction](start-transaction.html) | [hsqldb extensions for kontrol-db]<br>open override fun [startTransaction](start-transaction.html)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [tempTable](index.html#1773015801%2FFunctions%2F1975689373) | [hsqldb extensions for kontrol-db]<br>open fun [tempTable](index.html#1773015801%2FFunctions%2F1975689373)(table: Table): Table |


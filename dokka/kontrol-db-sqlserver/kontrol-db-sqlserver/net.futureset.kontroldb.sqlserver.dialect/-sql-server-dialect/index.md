//[kontrol-db-sqlserver](../../../index.md)/[net.futureset.kontroldb.sqlserver.dialect](../index.md)/[SqlServerDialect](index.md)

# SqlServerDialect

[sqlserver extensions for kontrol-db]\
class [SqlServerDialect](index.md) : AnsiDialect

## Constructors

| | |
|---|---|
| [SqlServerDialect](-sql-server-dialect.md) | [sqlserver extensions for kontrol-db]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [batchSeparator](batch-separator.md) | [sqlserver extensions for kontrol-db]<br>open override val [batchSeparator](batch-separator.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [closeQuote](close-quote.md) | [sqlserver extensions for kontrol-db]<br>open override val [closeQuote](close-quote.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [databaseName](database-name.md) | [sqlserver extensions for kontrol-db]<br>open override val [databaseName](database-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ddlInTransactions](ddl-in-transactions.md) | [sqlserver extensions for kontrol-db]<br>open override val [ddlInTransactions](ddl-in-transactions.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [literalFalse](literal-false.md) | [sqlserver extensions for kontrol-db]<br>open override val [literalFalse](literal-false.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalTrue](literal-true.md) | [sqlserver extensions for kontrol-db]<br>open override val [literalTrue](literal-true.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [nullableByDefault](nullable-by-default.md) | [sqlserver extensions for kontrol-db]<br>open override val [nullableByDefault](nullable-by-default.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [openQuote](open-quote.md) | [sqlserver extensions for kontrol-db]<br>open override val [openQuote](open-quote.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [order](order.md) | [sqlserver extensions for kontrol-db]<br>open override val [order](order.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 10 |
| [statementSeparator](statement-separator.md) | [sqlserver extensions for kontrol-db]<br>open override val [statementSeparator](statement-separator.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [supportsCatalogs](supports-catalogs.md) | [sqlserver extensions for kontrol-db]<br>open override val [supportsCatalogs](supports-catalogs.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true |
| [supportsTablespace](supports-tablespace.md) | [sqlserver extensions for kontrol-db]<br>open override val [supportsTablespace](supports-tablespace.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |

## Functions

| Name | Summary |
|---|---|
| [compareTo](index.md#84698461%2FFunctions%2F1857240192) | [sqlserver extensions for kontrol-db]<br>open operator override fun [compareTo](index.md#84698461%2FFunctions%2F1857240192)(other: DbDialect): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [connectionProps](index.md#-466960205%2FFunctions%2F1857240192) | [sqlserver extensions for kontrol-db]<br>open fun [connectionProps](index.md#-466960205%2FFunctions%2F1857240192)(): [Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html) |
| [dbNowTimestamp](db-now-timestamp.md) | [sqlserver extensions for kontrol-db]<br>open override fun [dbNowTimestamp](db-now-timestamp.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [endTransaction](end-transaction.md) | [sqlserver extensions for kontrol-db]<br>open override fun [endTransaction](end-transaction.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getNativeType](index.md#-1276212093%2FFunctions%2F1857240192) | [sqlserver extensions for kontrol-db]<br>open override fun [getNativeType](index.md#-1276212093%2FFunctions%2F1857240192)(columnType: ColumnType): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalDate](index.md#-326339082%2FFunctions%2F1857240192) | [sqlserver extensions for kontrol-db]<br>open fun [literalDate](index.md#-326339082%2FFunctions%2F1857240192)(date: [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [literalDatetime](index.md#-598586314%2FFunctions%2F1857240192) | [sqlserver extensions for kontrol-db]<br>open fun [literalDatetime](index.md#-598586314%2FFunctions%2F1857240192)(date: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [quote](index.md#2100218619%2FFunctions%2F1857240192) | [sqlserver extensions for kontrol-db]<br>open fun [quote](index.md#2100218619%2FFunctions%2F1857240192)(str: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [runScriptAgainstDb](run-script-against-db.md) | [sqlserver extensions for kontrol-db]<br>open override fun [runScriptAgainstDb](run-script-against-db.md)(emptyDb: [Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html), sqlScript: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) |
| [startTransaction](start-transaction.md) | [sqlserver extensions for kontrol-db]<br>open override fun [startTransaction](start-transaction.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [tempTable](temp-table.md) | [sqlserver extensions for kontrol-db]<br>open override fun [tempTable](temp-table.md)(table: Table): Table |

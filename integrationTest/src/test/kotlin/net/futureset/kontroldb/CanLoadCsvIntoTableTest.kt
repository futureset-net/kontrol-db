package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.StandardColumnTypes.Char
import net.futureset.kontroldb.StandardColumnTypes.DATE
import net.futureset.kontroldb.StandardColumnTypes.Decimal
import net.futureset.kontroldb.StandardColumnTypes.INT_16
import net.futureset.kontroldb.StandardColumnTypes.INT_64
import net.futureset.kontroldb.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.ApplyDsvToTable
import net.futureset.kontroldb.modelchange.addNotNull
import net.futureset.kontroldb.modelchange.addPrimaryKey
import net.futureset.kontroldb.modelchange.applyDsvToTable
import net.futureset.kontroldb.modelchange.createIndex
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.modelchange.executeQuery
import net.futureset.kontroldb.settings.SQL_TIMESTAMP_FORMAT
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.stopKoin
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import kotlin.io.path.readText
import kotlin.io.path.writeLines
import kotlin.io.path.writeText

@ExtendWith(DatabaseProvision::class)
internal class CanLoadCsvIntoTableTest {

    class CreateCustomerTable : Refactoring(
        executionOrder {
            ymd(2023, 8, 27)
            author("ben")
        },
        forward = changes {
            createTable {
                table("CUSTOMER")
                column("CUST_ID", INT_64)
                column("FIRSTNAME", Varchar(256))
                column("LASTNAME", Varchar(256))
                column("FAVOURITE_LETTER", Char(1))
                column("FAVOURITE_DECIMAL", Decimal(3, 2))
                column("IS_AN_IDIOT", BOOLEAN)
                column("NUMBER_OF_STAMPS", INT_16)
                column("DATE_OF_BIRTH", DATE)
                column("TIME_RIGHT_NOW", LOCALDATETIME)
            }
            createIndex {
                unique(true)
                indexName("UK_FIRSTNAME")
                table("CUSTOMER")
                column("FIRSTNAME")
            }
            addPrimaryKey {
                table {
                    name("CUSTOMER")
                }
                column("CUST_ID")
                constraintName("CUSTOMER_PK")
            }
            addNotNull {
                table {
                    name("CUSTOMER")
                }
                column("LASTNAME", Varchar(25))
            }
        },
        rollback = changes {
            dropTable {
                table("CUSTOMER")
            }
        },
    )

    class LoadACsvFile(
        csvFile: Path,
        seq: Short = 1,
        insertRows: Boolean = false,
        deleteRows: Boolean = false,
        updateRows: Boolean = false,
        ignoreInsertViolations: Boolean = false,
        delimiter: String = ",",
    ) : Refactoring(
        executionOrder {
            ymd(2023, 9, 16)
            author("ben")
            sequence(seq)
        },
        forward = changes {
            applyDsvToTable {
                useDbLoadingTool(false)
                file(csvFile.toString())
                insertRows(insertRows)
                deleteRows(deleteRows)
                updateRows(updateRows)
                ignoreInsertViolations(ignoreInsertViolations)
                separator(delimiter)

                table("CUSTOMER")
                columnMapping("CUST_ID", INT_64, primaryKey = true)
                columnMapping("FIRSTNAME", Varchar(256))
                columnMapping("LASTNAME", Varchar(256))
                columnMapping("FAVOURITE_LETTER", Char(1))
                columnMapping("FAVOURITE_DECIMAL", Decimal(3, 2))
                columnMapping("IS_AN_IDIOT", BOOLEAN)
                columnMapping("NUMBER_OF_STAMPS", INT_16)
                columnMapping("DATE_OF_BIRTH", DATE)
                columnMapping("TIME_RIGHT_NOW", LOCALDATETIME)
            }
        },
        rollback = emptyList(),

    )

    class LoadACsvFileStage2(
        csvFile: Path,
        seq: Short = 1,
        insertRows: Boolean = false,
        deleteRows: Boolean = false,
        updateRows: Boolean = false,
        ignoreInsertViolations: Boolean = false,
    ) : Refactoring(
        executionOrder {
            ymd(2023, 9, 16)
            author("ben")
            sequence(seq)
        },
        forward = changes {
            applyDsvToTable {
                useDbLoadingTool(false)
                file(csvFile.toString())
                insertRows(insertRows)
                deleteRows(deleteRows)
                updateRows(updateRows)
                ignoreInsertViolations(ignoreInsertViolations)

                table("CUSTOMER")
                columnMapping("CUST_ID", INT_64, primaryKey = true)
                columnMapping("FIRSTNAME", Varchar(256))
                columnMapping("LASTNAME", Varchar(256))
                columnMapping("FAVOURITE_LETTER", Char(1))
                columnMapping("FAVOURITE_DECIMAL", Decimal(3, 2))
                columnMapping("IS_AN_IDIOT", BOOLEAN)
                columnMapping("NUMBER_OF_STAMPS", INT_16)
                columnMapping("DATE_OF_BIRTH", DATE)
                columnMapping("TIME_RIGHT_NOW", LOCALDATETIME)
            }
        },
        rollback = emptyList(),

    )

    @ParameterizedTest
    @ValueSource(strings = ["|", ","])
    fun loadACsvWithInsertsOnly(delimiter: String, @TempDir tempDir: Path) {
        val dsvFile = tempDir.resolve("customers.dsv")
        val createCount = 8
        dsvFile.writeLines(
            listOf(
                "CUST_ID,FIRSTNAME,LASTNAME,FAVOURITE_LETTER,FAVOURITE_DECIMAL,IS_AN_IDIOT,NUMBER_OF_STAMPS,DATE_OF_BIRTH,TIME_RIGHT_NOW".split(
                    ",",
                ).joinToString(separator = delimiter),
            ) + generateSequence(
                1,
            ) { it + 1 }.takeWhile { it <= createCount }
                .map {
                    "$it,Ben$it,Riley$it,A,3.14,${it.let { r -> r % 2 }},$it,1974-10-26,${
                        LocalDateTime.now().format(SQL_TIMESTAMP_FORMAT)
                    }".split(",").joinToString(separator = delimiter)
                },
        )

        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                externalFileRoot(tempDir)
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    single { LoadACsvFile(get(), insertRows = true, delimiter = delimiter) }.bind(Refactoring::class)
                    single { Paths.get("customers.dsv") }
                },
            )
        }.use { result ->
            result.applySql()
            val customerCount = result.applySqlDirectly.withConnection {
                it.executeQuery("SELECT COUNT(*) FROM CUSTOMER") { rs ->
                    rs.getInt(1)
                }.first()
            }
            assertThat(customerCount).isEqualTo(createCount)
            assertThat(
                result.applySqlDirectly.withConnection {
                    it.executeQuery("SELECT COUNT(*) FROM CUSTOMER WHERE IS_AN_IDIOT=1") { rs ->
                        rs.getInt(1)
                    }.first()
                },
            ).isEqualTo(customerCount / 2)
        }
    }

    data class DoUpdates(
        val inserts: Boolean,
        val updates: Boolean,
        val deletes: Boolean,
        val ignoreInsertViolations: Boolean,
    )

    companion object {
        @JvmStatic
        fun loadACsvThenDoSomeUpdates() = listOf(
            DoUpdates(true, true, true, false),
            DoUpdates(true, true, false, false),
            DoUpdates(true, false, false, true),
            DoUpdates(false, true, false, false),
        )

        @JvmStatic
        fun errorScenarios() = listOf<ErrorScenario>(
            ErrorScenario("Path /afile must be relative") {
                table("fred")
                file("/afile")
            },
            ErrorScenario("Must specify primary key for insert or updates") {
                table("fred")
                file("afile.dsv")
            },
            ErrorScenario("Must specify primary key for insert or updates") {
                table("fred")
                file("afile.dsv")
                columnMapping("TEST_COLUMN", Varchar(20), "TEST", primaryKey = false)
                updateRows(true)
                insertRows(false)
            },
            ErrorScenario("Cannot ignore insert violations if insert rows is not set") {
                table("fred")
                file("afile.dsv")
                columnMapping("TEST_COLUMN", Varchar(20), "TEST", primaryKey = true)
                ignoreInsertViolations(true)
                insertRows(false)
            },
        )
    }

    @ParameterizedTest
    @MethodSource
    fun loadACsvThenDoSomeUpdates(arg: DoUpdates, @TempDir tempDir: Path) {
        val dsvFile = tempDir.resolve("customers.dsv")
        val createCount = 8
        dsvFile.writeLines(
            listOf("CUST_ID,FIRSTNAME,LASTNAME,FAVOURITE_LETTER,FAVOURITE_DECIMAL,IS_AN_IDIOT,NUMBER_OF_STAMPS,DATE_OF_BIRTH,TIME_RIGHT_NOW") + generateSequence(
                1,
            ) { it + 1 }.takeWhile { it <= createCount }
                .map {
                    "$it,Ben$it,Riley$it,C,3.14,${it.let { r -> r % 2 }},$it,1974-10-26,${
                        LocalDateTime.now().format(SQL_TIMESTAMP_FORMAT)
                    }"
                },
        )
        assertThat(dsvFile).exists()
        val text = dsvFile.readText()
        val editedText = text.replace("Riley3", "RileyChanged")
        assertThat(editedText).isNotEqualTo(text)
        val lineDeletedText = editedText.lines().filterNot { it.contains("Riley5") }.joinToString("\n")
        assertThat(lineDeletedText).isNotEqualTo(text)
        val dsvFile2 = tempDir.resolve("customers2.dsv")
        dsvFile2.writeText(lineDeletedText)
        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                externalFileRoot(tempDir)
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    single { LoadACsvFile(dsvFile.fileName, insertRows = true) }.bind(Refactoring::class)
                    single {
                        LoadACsvFileStage2(
                            dsvFile2.fileName,
                            seq = 2,
                            insertRows = arg.inserts,
                            updateRows = arg.updates,
                            deleteRows = arg.deletes,
                            ignoreInsertViolations = arg.ignoreInsertViolations,
                        )
                    }.bind(Refactoring::class)
                },
            )
        }.use { result ->
            result.applySql()
            val customerCount = result.applySqlDirectly.withConnection {
                it.executeQuery("SELECT COUNT(*) FROM CUSTOMER") { rs ->
                    rs.getInt(1)
                }.first()
            }
            val updateRecordCount = result.applySqlDirectly.withConnection {
                it.executeQuery("SELECT COUNT(*) FROM CUSTOMER WHERE LASTNAME='RileyChanged'") { rs ->
                    rs.getInt(1)
                }.first()
            }
            assertThat(updateRecordCount).isEqualTo(if (arg.updates) 1 else 0)

            if (arg.deletes) {
                assertThat(customerCount).describedAs("A line has been deleted").isEqualTo(createCount - 1)
            } else {
                assertThat(customerCount).describedAs("No lines deleted").isEqualTo(createCount)
            }
        }
    }

    class ParameterizableCsvLoad(lambda: ApplyDsvToTable.ApplyDsvToTableBuilder.() -> Unit) : Refactoring(
        executionOrder {
            ymd(2023, 10, 27)
            author("ben")
        },
        forward = changes {
            applyDsvToTable(lambda)
        },
        rollback = emptyList(),
    )

    data class ErrorScenario(
        val message: String,
        val lambda: ApplyDsvToTable.ApplyDsvToTableBuilder.() -> Unit,
    )

    @ParameterizedTest
    @MethodSource("errorScenarios")
    fun errorScenarios(
        errorScenario: ErrorScenario,
        @TempDir tempDir: Path,
    ) {
        runCatching {
            dsl {
                loadConfig("test-config.yml")
                executionSettings {
                    externalFileRoot(tempDir)
                }
                changeModules(
                    module {
                        single { ParameterizableCsvLoad(errorScenario.lambda) }.bind(Refactoring::class)
                    },
                )
            }
        }.onFailure {
            assertThat(it.cause?.cause?.message).isEqualTo(errorScenario.message)
            stopKoin()
        }
            .onSuccess {
                fail("Should fail")
            }
    }
}

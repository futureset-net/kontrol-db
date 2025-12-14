package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.dsl.createTable
import net.futureset.kontroldb.dsl.exportData
import net.futureset.kontroldb.dsl.insertRowsInto
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.nio.file.Path

@ExtendWith(DatabaseProvision::class)
internal class CreateTemporaryTableTest {
    class CreateATemporaryTable(
        tableType: TablePersistence,
    ) : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward =
        changes {
            createTable("fred") {
                when (tableType) {
                    TablePersistence.GLOBAL_TEMPORARY -> asGlobalTemporaryTable()
                    TablePersistence.TEMPORARY -> asLocalTemporaryTable()
                    TablePersistence.NORMAL -> table("fred")
                }
                column("TEST_COLUMN", Varchar(256))
            }
            insertRowsInto("fred") {
                when (tableType) {
                    TablePersistence.GLOBAL_TEMPORARY -> asGlobalTemporaryTable()
                    TablePersistence.TEMPORARY -> asLocalTemporaryTable()
                    TablePersistence.NORMAL -> table("fred")
                }
                row {
                    value("TEST_COLUMN", "HELLO")
                }
            }
            exportData {
                selectFrom("fred") {
                    when (tableType) {
                        TablePersistence.GLOBAL_TEMPORARY -> asGlobalTemporaryTable()
                        TablePersistence.TEMPORARY -> asLocalTemporaryTable()
                        TablePersistence.NORMAL -> table("fred")
                    }
                    column("TEST_COLUMN")
                }
                outputFile("text.dsv")
            }
        },
        rollback = emptyList(),
    )

    @ParameterizedTest
    @EnumSource(value = TablePersistence::class, mode = EnumSource.Mode.EXCLUDE, names = ["NORMAL"])
    fun `Can create a temporary table`(
        tableType: TablePersistence,
        @TempDir outputDir: Path,
    ) {
        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                outputDirectory(outputDir)
            }
            changeModules(
                module {
                    singleOf(::CreateATemporaryTable).bind(Refactoring::class)
                    single { tableType }
                },
            )
        }.use { engine ->

            engine.applySql()
            val testCsv = outputDir.resolve("text.dsv")
            assertThat(testCsv.readDsvToMapList()).hasSize(1)
        }
    }

    class CreateATemporaryTableFromAQuery :
        Refactoring(
            executionOrder {
                ymd(2023, 9, 24)
                author("ben")
            },
            forward =
            changes {
                createTable("fred") {
                    asGlobalTemporaryTable()
                    column("TEST_COLUMN", Varchar(256))
                    selectFrom("KONTROL_DB_VERSIONING") {
                        column("TEST_COLUMN", "\"ID\"")
                    }
                }
                exportData {
                    selectFrom("fred") {
                        asGlobalTemporaryTable()
                        column("TEST_COLUMN")
                    }
                    separator("|")
                    outputFile("text.dsv")
                }
                exportData {
                    selectFrom("fred") {
                        asGlobalTemporaryTable()
                        column("TEST_COLUMN")
                    }
                    separator("|")
                }
            },
            rollback = emptyList(),
        )

    @Test
    fun `Create a temporary table from a query`(
        @TempDir outputDir: Path,
    ) {
        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                outputDirectory(outputDir)
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateATemporaryTableFromAQuery).bind(Refactoring::class)
                },
            )
        }.use { engine ->

            engine.applySql()

            val testCsv = outputDir.resolve("text.dsv")
            assertThat(testCsv.readDsvToMapList()).hasSize(3)
        }
    }
}

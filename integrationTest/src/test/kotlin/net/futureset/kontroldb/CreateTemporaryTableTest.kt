package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.exportData
import net.futureset.kontroldb.modelchange.insertRows
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class CreateTemporaryTableTest {

    class CreateATemporaryTable(tableType: TablePersistence) : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTable {
                when (tableType) {
                    TablePersistence.GLOBAL_TEMPORARY -> globalTemporaryTable("fred")
                    TablePersistence.TEMPORARY -> localTemporaryTable("fred")
                    TablePersistence.NORMAL -> table("fred")
                }
                column("TEST_COLUMN", Varchar(256))
            }
            insertRows {
                when (tableType) {
                    TablePersistence.GLOBAL_TEMPORARY -> globalTemporaryTable("fred")
                    TablePersistence.TEMPORARY -> localTemporaryTable("fred")
                    TablePersistence.NORMAL -> table("fred")
                }
                row {
                    value("TEST_COLUMN", "HELLO")
                }
            }
            exportData {
                query {
                    when (tableType) {
                        TablePersistence.GLOBAL_TEMPORARY -> globalTemporaryTable("fred")
                        TablePersistence.TEMPORARY -> localTemporaryTable("fred")
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
    fun `Can create a temporary table`(tableType: TablePersistence) {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateATemporaryTable).bind(Refactoring::class)
                    single { tableType }
                },
            )
        }.use { engine ->

            engine.applySql()
            val testCsv = engine.effectiveSettings.externalFileRoot.resolve("text.dsv")
            assertThat(testCsv.readDsvToMapList()).hasSize(1)
        }
    }

    class CreateATemporaryTableFromAQuery() : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTable {
                globalTemporaryTable("fred")
                column("TEST_COLUMN", Varchar(256))
                fromQuery {
                    column("TEST_COLUMN", "ID")
                    table("KONTROL_DB_VERSIONING")
                }
            }
            exportData {
                query {
                    globalTemporaryTable("fred")
                    column("TEST_COLUMN")
                }
                separator("|")
                outputFile("text.dsv")
            }
            exportData {
                query {
                    globalTemporaryTable("fred")
                    column("TEST_COLUMN")
                }
                separator("|")
            }
        },
        rollback = emptyList(),
    )

    @Test
    fun `Create a temporary table from a query`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateATemporaryTableFromAQuery).bind(Refactoring::class)
                },
            )
        }.use { engine ->

            engine.applySql()

            val testCsv = engine.effectiveSettings.externalFileRoot.resolve("text.dsv")
            assertThat(testCsv.readDsvToMapList()).hasSize(3)
        }
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.modelchange.createTemporaryTable
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal class CreateTemporaryTableTest {

    class CreateATemporaryTable(tableType: TablePersistence) : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTemporaryTable {
                table("fred")
                tableType(tableType)
                column("TEST_COLUMN", Varchar(256))
            }
        },
        rollback = emptyList(),
    )

    @ParameterizedTest
    @EnumSource(TablePersistence::class)
    fun `Can create a temporary table`(tableType: TablePersistence) {
        KontrolDb.dsl {
            changeModules(
                module {
                    singleOf(::CreateATemporaryTable).bind(Refactoring::class)
                    single { tableType }
                },
            )
        }.use { engine ->

            engine.applySql()

            assertThat(
                engine.sqlExecutor.withConnection {
                    it.executeQuery("""SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES where table_name='fred'""") { rs ->
                        rs.getInt(1)
                    }.first()
                },
            ).isEqualTo(if (tableType == TablePersistence.GLOBAL_TEMPORARY) 1 else 0)
        }
    }

    class CreateATemporaryTableFromAQuery() : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTemporaryTable {
                table("fred")
                tableType(TablePersistence.GLOBAL_TEMPORARY)
                column("TEST_COLUMN", Varchar(256))
                fromQuery {
                    column("TEST_COLUMN", "ID")
                    table("KONTROL_DB_VERSIONING")
                }
            }
        },
        rollback = emptyList(),
    )

    @Test
    fun `Create a temporary table from a query`() {
        KontrolDb.dsl {
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateATemporaryTableFromAQuery).bind(Refactoring::class)
                },
            )
        }.use { engine ->

            engine.applySql()

            assertThat(
                engine.sqlExecutor.withConnection {
                    it.executeQuery("""SELECT COUNT(*) FROM "fred"""") { rs ->
                        rs.getInt(1)
                    }.first()
                },
            ).isZero()
        }
    }
}

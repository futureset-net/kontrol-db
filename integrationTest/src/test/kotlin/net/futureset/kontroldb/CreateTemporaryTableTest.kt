package net.futureset.kontroldb

import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.modelchange.createTemporaryTable
import org.assertj.core.api.Assertions.assertThat
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
        val engine = KontrolDbDsl.kontrolDb {
            changeModules(
                module {
                    singleOf(CreateTemporaryTableTest::CreateATemporaryTable).bind(Refactoring::class)
                    single { tableType }
                },
            )
        }

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

package net.futureset.kontroldb

import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.PredicateBuilder
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.delete
import net.futureset.kontroldb.modelchange.insertOrUpdateRow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal class InsertUpdateAndDeleteTest {

    class CreateATable(param: PredicateBuilder.() -> Unit) : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTable {
                table("fred")
                column("TEST_COLUMN", INT_32)
            }
            insertOrUpdateRow {
                table("fred")
                for (i in 1..100) {
                    values {
                        value("TEST_COLUMN", i)
                    }
                }
                primaryKey("TEST_COLUMN")
                mode(UpdateMode.INSERT)
            }
            delete {
                table("fred")
                where(param)
            }
        },
        rollback = emptyList(),
    )

    data class Param(
        val expectedResultCount: Int,
        val selectQuery: PredicateBuilder.() -> Unit,
    )

    @ParameterizedTest
    @MethodSource("fred")
    fun `Check various predicates`(param: Param) {
        val engine = KontrolDbDsl.kontrolDb {
            changeModules(
                module {
                    singleOf(InsertUpdateAndDeleteTest::CreateATable).bind(Refactoring::class)
                    single { param.selectQuery }
                },
            )
        }

        engine.applySql()

        assertThat(
            engine.sqlExecutor.withConnection {
                it.executeQuery("""SELECT COUNT(*) FROM "fred"""") { rs ->
                    rs.getInt(1)
                }.first()
            },
        ).isEqualTo(param.expectedResultCount)
    }

    companion object {
        @JvmStatic
        fun fred() = listOf(
            Param(99) {
                col("TEST_COLUMN") eq literal(5)
            },
            Param(95) {
                col("TEST_COLUMN") lt literal(6)
            },
        )
    }
}

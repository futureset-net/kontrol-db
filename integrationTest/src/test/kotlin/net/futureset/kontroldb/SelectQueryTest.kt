package net.futureset.kontroldb

import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.PredicateBuilder
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.insert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal class SelectQueryTest {

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
            insert {
                table("fred")
                for (i in 1..100) {
                    values {
                        value("TEST_COLUMN", i)
                    }
                }
            }
            createTable {
                table("results")
                column("TEST_COLUMN", Varchar(256))
            }
            insert {
                table("results")

                fromQuery {
                    table("fred")
                    column("TEST_COLUMN")
                    where(param)
                }
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
                    singleOf(SelectQueryTest::CreateATable).bind(Refactoring::class)
                    single { param.selectQuery }
                },
            )
        }

        engine.applySql()

        assertThat(
            engine.sqlExecutor.withConnection {
                it.executeQuery("""SELECT COUNT(*) FROM "results"""") { rs ->
                    rs.getInt(1)
                }.first()
            },
        ).isEqualTo(param.expectedResultCount)
    }

    companion object {
        @JvmStatic
        fun fred() = listOf(
            Param(1) {
                col("TEST_COLUMN") eq literal(5)
            },
            Param(5) {
                col("TEST_COLUMN") lt literal(6)
            },
            Param(6) {
                col("TEST_COLUMN") lte literal(6)
            },
            Param(4) {
                col("TEST_COLUMN") gt literal(96)
            },
            Param(5) {
                col("TEST_COLUMN") gte literal(96)
            },
            Param(9) {
                col("TEST_COLUMN") gt literal(10)
                col("TEST_COLUMN") lt literal(20)
            },
            Param(6) {
                col("TEST_COLUMN") between (literal(10) to literal(15))
            },
            Param(100) {
                anyOf {
                    col("TEST_COLUMN") gt literal(10)
                    col("TEST_COLUMN") lt literal(20)
                }
            },
        )
    }
}

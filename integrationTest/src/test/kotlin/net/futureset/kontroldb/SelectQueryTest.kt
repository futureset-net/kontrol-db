package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.PredicateBuilder
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.insertRowsInto
import net.futureset.kontroldb.modelchange.select
import net.futureset.kontroldb.refactoring.Refactoring
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(DatabaseProvision::class)
internal class SelectQueryTest {

    class CreateATable(param: PredicateBuilder.() -> Unit) : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTable("fred") {
                column("TEST_COLUMN", INT32)
                column("STRING_COLUMN", Varchar(5)) {
                    notNull()
                }
            }
            insertRowsInto("fred") {
                for (i in 1..100) {
                    row {
                        value("TEST_COLUMN", i)
                        value("STRING_COLUMN", ('A' + (i % 10)).toString())
                    }
                }
            }
            createTable("results") {
                column("TEST_COLUMN", Varchar(256)) {
                    nullable()
                }
                column("STRING_COLUMN", Varchar(5))
            }
            insertRowsInto("results") {
                fromQuery {
                    table("fred")
                    column("TEST_COLUMN")
                    column("STRING_COLUMN")
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
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(SelectQueryTest::CreateATable).bind(Refactoring::class)
                    single { param.selectQuery }
                },
            )
        }.use { engine ->

            engine.applySql()

            assertThat(
                engine.applySqlDirectly.withConnection {
                    it.executeQuery("""SELECT COUNT(*) FROM "results"""") { rs ->
                        rs.getInt(1)
                    }.first()
                },
            ).isEqualTo(param.expectedResultCount)
        }
    }

    class SelectResults : Refactoring(
        executionOrder {
            ymd(2023, 10, 27)
            author("ben")
        },
        forward = changes {
            select {
                tableWithAlias("results", "A")
                column("STRING_COLUMN")
                column("TEST_COLUMN")
                column("ANOTHER", "\"TEST_COLUMN\"")
            }
        },
        rollback = emptyList(),
    )

    @Test
    fun `select results into a console`() {
        val selectQuery: PredicateBuilder.() -> Unit = {}
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(SelectQueryTest::CreateATable).bind(Refactoring::class)
                    singleOf(SelectQueryTest::SelectResults).bind(Refactoring::class)
                    single { selectQuery }
                },
            )
        }.use { engine ->
            engine.applySql()
        }
    }

    companion object {
        @JvmStatic
        fun fred() = listOf(
            Param(1) {
                "TEST_COLUMN".column() eq 5
            },
            Param(1) {
                5.literal() eq "TEST_COLUMN".column()
            },
            Param(5) {
                "TEST_COLUMN".column() lt 6
            },
            Param(94) {
                6.literal() lt "TEST_COLUMN".column()
            },
            Param(6) {
                "TEST_COLUMN".column() lte 6
            },
            Param(95) {
                6.literal() lte "TEST_COLUMN".column()
            },
            Param(4) {
                "TEST_COLUMN".column() gt 96
            },
            Param(5) {
                "TEST_COLUMN".column() gte 96
            },
            Param(9) {
                "TEST_COLUMN".column() gt 10
                "TEST_COLUMN".column() lt 20
            },
            Param(10) {
                "STRING_COLUMN".column() eq "A"
            },
            Param(6) {
                "TEST_COLUMN".column() inRangeOf (10 to 15)
            },
            Param(40) {
                "STRING_COLUMN".column() inRangeOf ("F" to "I")
            },
            Param(20) {
                "STRING_COLUMN".column() gt "H"
            },
            Param(20) {
                "H".literal() lt "STRING_COLUMN".column()
            },
            Param(10) {
                "STRING_COLUMN".column() lt "B"
            },
            Param(30) {
                "STRING_COLUMN".column() gte "H"
            },
            Param(20) {
                "STRING_COLUMN".column() lte "B"
            },
            Param(100) {
                true.literal() eq true
            },
            Param(0) {
                true.literal() eq false
            },
            Param(9) {
                allOf {
                    "TEST_COLUMN".column() gt "1+9".expression()
                    "TEST_COLUMN".column() lt "15+5".expression()
                }
            },
            Param(100) {
                allOf {
                    LocalDate.of(2023, 10, 1).literal() gt LocalDate.of(2023, 9, 1)
                    LocalDateTime.of(2023, 10, 1, 22, 0).literal() gt LocalDateTime.of(2023, 9, 1, 22, 0)
                }
            },
            Param(9) {
                allOf {
                    "TEST_COLUMN".column() gt 10
                    "TEST_COLUMN".column() lt 20
                }
            },
            Param(100) {
                anyOf {
                    "TEST_COLUMN".column() gt 10
                    "TEST_COLUMN".column() lt 20
                    allOf {}
                }
            },
            Param(100) {
                anyOf {}
            },
            Param(1) {
                anyOf { "TEST_COLUMN".column() eq 99 }
            },
            Param(3) {
                allOf {
                    anyOf {
                        "TEST_COLUMN".column() eq 10
                        "TEST_COLUMN".column() eq 20
                        anyOf { "TEST_COLUMN".column() eq 21 }
                    }
                    "TEST_COLUMN".column() lt 30
                    anyOf {}
                }
            },
        )
    }
}

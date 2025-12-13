package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.insertRowsInto
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.settings.TransactionScope
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
class FailedMigrationScenariosTest {
    @ParameterizedTest
    @MethodSource("scenarios")
    fun expectedFailurePosition(
        expectedScenario: ExpectedScenario,
        @DialectName dialectName: String,
    ) {
        Assumptions.assumeFalse(expectedScenario.transactionScope != TransactionScope.STATEMENT && dialectName == "oracle")
        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                transactionScope(expectedScenario.transactionScope)
            }
            changeModules(
                module {
                    singleOf(::FirstSuccessfulStep).bind(Refactoring::class)
                    singleOf(::SecondFailureStep).bind(Refactoring::class)
                    singleOf(::ThirdSuccessfulStep).bind(Refactoring::class)
                },
            )
        }.use { engine ->
            runCatching {
                engine.applySql()
            }.onFailure {
                val versionControlTableExists =
                    1 ==
                        engine.applySqlDirectly.withConnection {
                            it
                                .executeQuery(
                                    "SELECT COUNT(*) FROM ${if (dialectName == "oracle") "ALL_TABLES" else "INFORMATION_SCHEMA.TABLES"} WHERE TABLE_NAME='KONTROL_DB_VERSIONING'",
                                ) { rs ->
                                    rs.getInt(1)
                                }.first()
                        }
                if (versionControlTableExists) {
                    val stage =
                        engine.applySqlDirectly.withConnection {
                            it
                                .executeQuery(
                                    "SELECT MAX(${engine.effectiveSettings.quote(
                                        "STAGE_NUM",
                                    )}) FROM ${engine.effectiveSettings.quote("TEST")}",
                                ) { rs ->
                                    rs.getInt(1)
                                }.first()
                        }
                    assertThat(stage).isEqualTo(expectedScenario.expectedStage)
                } else {
                    assertThat(0).isEqualTo(expectedScenario.expectedStage)
                }
            }.onSuccess {
                fail("should never succeed")
            }
        }
    }

    data class ExpectedScenario(
        val transactionScope: TransactionScope,
        val expectedStage: Int,
    )

    companion object {
        @JvmStatic
        fun scenarios(): List<ExpectedScenario> = listOf(
            ExpectedScenario(TransactionScope.STATEMENT, 2),
            ExpectedScenario(TransactionScope.MIGRATION, 0),
            ExpectedScenario(TransactionScope.REFACTORING, 1),
        )
    }

    class FirstSuccessfulStep :
        Refactoring(
            executionOrder {
                ymd(2023, 10, 29)
                author("ben")
            },
            forward =
            changes {
                createTable("TEST") {
                    column("STAGE_NUM", INT32)
                    column("STAGE_NAME", Varchar(32))
                }
                insertRowsInto("TEST") {
                    row {
                        value("STAGE_NUM", 1)
                        value("STAGE_NAME", "FIRST")
                    }
                }
            },
            rollback = emptyList(),
        )

    class SecondFailureStep :
        Refactoring(
            executionOrder {
                ymd(2023, 10, 29)
                author("ben")
            },
            forward =
            changes {
                insertRowsInto("TEST") {
                    row {
                        value("STAGE_NUM", 2)
                        value("STAGE_NAME", "TWO")
                    }
                }
                insertRowsInto("NO_SUCH_TABLE") {
                    row {
                        value("STAGE_NUM", 3)
                        value("STAGE_NAME", "TWO")
                    }
                }
                insertRowsInto("TEST") {
                    row {
                        value("STAGE_NUM", 4)
                        value("STAGE_NAME", "TWOANDABIT")
                    }
                }
            },
            rollback = emptyList(),
        )

    class ThirdSuccessfulStep :
        Refactoring(
            executionOrder {
                ymd(2023, 10, 29)
                author("ben")
            },
            forward =
            changes {
                insertRowsInto("TEST") {
                    row {
                        value("STAGE_NUM", 5)
                        value("STAGE_NAME", "THREE")
                    }
                }
            },
            rollback = emptyList(),
        )
}

package net.futureset.kontroldb

import ch.qos.logback.classic.spi.ILoggingEvent
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assumptions.assumeThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(DatabaseProvision::class)
class ProtectedConnectionTest {
    @Test
    fun `check cannot commit connection because it is managed by transaction`(
        @DialectName dialect: String,
    ) {
        assumeThat(dialect).isEqualTo("hsqldb")
        KontrolDbEngineBuilder
            .dsl {
                loadConfig("test-config.yml")
            }.use { engine ->
                val logs =
                    capturingLogEvents {
                        engine.applySqlDirectly.wrapInTransactionOnWhen(true) {
                            engine.applySqlDirectly.withConnection { conn ->
                                conn.commit()
                            }
                        }
                    }
                assertThat(logs.mapNotNull(ILoggingEvent::getMessage))
                    .contains("Attempt to commit transaction ignored. use ConnectionHolder.withTransaction")
            }
    }

    @Test
    fun `check cannot close connection because it is managed by transaction`(
        @DialectName dialect: String,
    ) {
        assumeThat(dialect).isEqualTo("hsqldb")
        KontrolDbEngineBuilder
            .dsl {
                loadConfig("test-config.yml")
            }.use { engine ->
                val logs =
                    capturingLogEvents {
                        engine.applySqlDirectly.wrapInTransactionOnWhen(true) {
                            engine.applySqlDirectly.withConnection { conn ->
                                conn.close()
                            }
                        }
                    }
                assertThat(logs.mapNotNull(ILoggingEvent::getMessage))
                    .contains("Ignore connection.close")
            }
    }

    @Test
    fun `check change autocommit state because it is managed by transaction`(
        @DialectName dialect: String,
    ) {
        assumeThat(dialect).isEqualTo("hsqldb")
        KontrolDbEngineBuilder
            .dsl {
                loadConfig("test-config.yml")
            }.use { engine ->
                val logs =
                    capturingLogEvents {
                        engine.applySqlDirectly.wrapInTransactionOnWhen(true) {
                            engine.applySqlDirectly.withConnection { conn ->
                                conn.autoCommit = true
                            }
                        }
                    }
                assertThat(logs.mapNotNull(ILoggingEvent::getMessage))
                    .contains("Attempt to change autocommit state to true failed")
            }
    }
}

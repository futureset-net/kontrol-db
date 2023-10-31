package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
class DifferentSchemaOrCatalogTest {

    @Test
    fun `run in different default schema`(@DialectName dialect: String) {
        assumeTrue(dialect == "hsqldb")
        dsl {
            loadConfig("test-config.yml")
            dbSettings {
                defaultSchema("HELLO")
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).isGreaterThan(1)
        }
    }

    @Test
    fun `run in different default catalog`(@DialectName dialect: String) {
        assumeTrue(dialect == "hsqldb")
        dsl {
            loadConfig("test-config.yml")
            dbSettings {
                defaultCatalog("BYE")
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).isGreaterThan(1)
        }
    }

    @Test
    fun `run in different default catalog and schema`(@DialectName dialect: String) {
        assumeTrue(dialect == "hsqldb")
        dsl {
            loadConfig("test-config.yml")
            dbSettings {
                defaultCatalog("BYE")
                defaultSchema("HELLO")
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).isGreaterThan(1)
        }
    }
}

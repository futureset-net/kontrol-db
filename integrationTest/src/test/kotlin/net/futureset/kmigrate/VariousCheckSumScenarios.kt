package net.futureset.kmigrate

import net.futureset.kmigrate.KMigrateDsl.Companion.kmigrate
import net.futureset.kmigrate.test.petstore.IncrementCustomerId
import net.futureset.kmigrate.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.koin.ksp.generated.module
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object VariousCheckSumScenarios : Spek({

    Feature("Can migrate Petstore to HSQLDB") {

        Scenario("Refactorings can be re-run where indicated when the checksum changes") {
            val result by memoized {
                kmigrate {
                    changeModules(PetStore().module)
                }
            }
            Given("No changes have been applied") {
                assertThat(result.getCurrentState()).isEmpty()
            }
            And("Migration is created") {
                assertThat(result.applySql()).isGreaterThan(2)
            }
            And("Some changes have been applied") {
                assertThat(result.getCurrentState()).hasSizeGreaterThan(2)
            }
            When("we mess around with the recorded checksum to fake a modification") {
                result.sqlExecutor.withConnection {
                    it.executeSql("UPDATE K_MIGRATE_CONTROL SET CHECK_SUM='INVALID' WHERE ID='${IncrementCustomerId::class.qualifiedName}'")
                }
            }
            Then("applying again re-ran the changed item") {
                assertThat(result.applySql()).isGreaterThan(1)
                assertThat(
                    result.sqlExecutor.withConnection {
                        it.executeQuery("SELECT EXECUTION_COUNT FROM K_MIGRATE_CONTROL WHERE ID='${IncrementCustomerId::class.qualifiedName}'") {
                            it.getInt(1)
                        }.first()
                    },
                ).isEqualTo(2)
            }
        }
    }
})

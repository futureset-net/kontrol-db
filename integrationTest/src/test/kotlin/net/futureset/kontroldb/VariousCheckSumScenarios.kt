package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.test.petstore.IncrementCustomerId
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.koin.ksp.generated.module
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object VariousCheckSumScenarios : Spek({

    Feature("Can migrate Petstore to HSQLDB") {

        Scenario("Refactorings can be re-run where indicated when the checksum changes") {
            val result by memoized {
                kontrolDb {
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
                    it.executeSql("UPDATE $DEFAULT_VERSION_CONTROL_TABLE SET CHECK_SUM='INVALID' WHERE ID='${IncrementCustomerId::class.qualifiedName}'")
                }
            }
            Then("applying again re-ran the changed item") {
                assertThat(result.applySql()).isGreaterThan(1)
                assertThat(
                    result.sqlExecutor.withConnection { conn ->
                        conn.executeQuery("SELECT EXECUTION_COUNT FROM $DEFAULT_VERSION_CONTROL_TABLE WHERE ID='${IncrementCustomerId::class.qualifiedName}'") {
                            it.getInt(1)
                        }.first()
                    },
                ).isEqualTo(2)
            }
        }
    }
})

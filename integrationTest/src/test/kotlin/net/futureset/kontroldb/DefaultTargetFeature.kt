package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object DefaultTargetFeature : Spek({

    Feature("Default target") {

        Scenario("Check default target") {
            val result by memoized {
                kontrolDb {
                }
            }
            When("Migration is created") {
                assertThat(result).isNotNull
            }
            Then("default target database is HSQLDB") {
                assertThat(result.effectiveSettings.jdbcUrl)
                    .isEqualTo("jdbc:hsqldb:mem:testdb")
            }
        }
    }
})

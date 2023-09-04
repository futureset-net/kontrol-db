package net.futureset.kmigrate

import net.futureset.kmigrate.KMigrateDsl.Companion.kmigrate
import net.futureset.kmigrate.modelchange.CreateTable
import net.futureset.kmigrate.refactoring.CreateVersionControlTable
import net.futureset.kmigrate.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object CreateVersionControlTableFeature : Spek({

    Feature("Create a version control table") {

        Scenario("Has a version control table by default") {
            val result by memoized {
                kmigrate {
                }
            }
            When("its started") {
                assertThat(result).isNotNull
            }
            Then("it should have a size of 1") {
                assertThat(result.refactorings).hasSize(1)
            }
            Then("it should have a version control entry") {
                assertThat(result.refactorings.first()).isInstanceOf(CreateVersionControlTable::class.java)
                assertThat(result.refactorings.first() as CreateVersionControlTable)
                    .extracting { x -> x.forward.first() as CreateTable }
                    .extracting(CreateTable::table)
                    .extracting(SchemaObject::name)
                    .extracting(DbIdentifier::name)
                    .isEqualTo(DEFAULT_VERSION_CONTROL_TABLE)
            }
        }

        val customName = "HELLO"
        Scenario("Check has a version control table with custom name $customName") {
            val result by memoized {
                kmigrate {

                    dbSettings {
                        versionControlTable {
                            name(customName)
                        }
                        defaultTablespace("TEST_TABLESPACE")
                    }
                }
            }
            When("I change the version control table name") {
                assertThat(result).isNotNull
            }
            Then("it should have a size of 1") {
                assertThat(result.refactorings).hasSize(1)
            }
            Then("it should have a version control entry with table named $customName") {
                assertThat(result.refactorings.first()).isInstanceOf(CreateVersionControlTable::class.java)
                assertThat(result.refactorings.first() as CreateVersionControlTable)
                    .extracting { x -> x.forward.first() as CreateTable }
                    .extracting(CreateTable::table)
                    .extracting(SchemaObject::name)
                    .extracting(DbIdentifier::name)
                    .isEqualTo(customName)
            }
        }
    }
})

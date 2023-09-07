package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.nio.file.Files
import kotlin.io.path.readText

object CreateVersionControlTableFeature : Spek({

    Feature("Create a version control table") {

        Scenario("Has a version control table by default") {
            val result by memoized {
                kontrolDb {
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
            val tempDir by memoized(factory = {
                Files.createTempDirectory("temp")
            }) { it.toFile().deleteRecursively() }
            val result by memoized {
                kontrolDb {

                    dbSettings {
                        versionControlTable {
                            name(customName)
                            schema("PUBLIC")
                            catalog("PUBLIC")
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
            Then("can generate a script") {
                val script = tempDir.resolve("output.sql")
                result.generateSql(script)
                assertThat(script).exists()
                println(script.readText())
            }
        }
    }
})

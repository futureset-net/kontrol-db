package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.koin.ksp.generated.module
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.readText

object ApplyChangesToHsqlDb : Spek({

    Feature("Can migrate Petstore to HSQLDB") {

        Scenario("Apply to sql script") {
            val tempDir by memoized(factory = {
                Files.createTempDirectory("temp")
            }) { it.toFile().deleteRecursively() }
            val result by memoized {
                kontrolDb {
                    changeModules(PetStore().module)
                }
            }
            lateinit var generatedFile: Path
            When("Migration is created") {
                generatedFile = tempDir.resolve("output.sql")
                result.generateSql(generatedFile)
            }
            Then("script was generated") {
                assertThat(generatedFile).exists()
                println(generatedFile.readText())
            }
        }
        Scenario("Apply to HSQLDB") {
            val result by memoized {
                kontrolDb {
                    changeModules(PetStore().module)
                }
            }
            Given("No changes have been applied") {
                assertThat(result.getCurrentState()).isEmpty()
            }
            When("Migration is created") {
                assertThat(result.applySql()).isGreaterThan(2)
            }
            Then("Some changes have been applied") {
                assertThat(result.getCurrentState()).hasSizeGreaterThan(2)
            }
            Then("applying again should not do anything") {
                assertThat(result.applySql()).isZero()
            }
        }
    }
})

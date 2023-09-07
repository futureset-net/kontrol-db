package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.targetsystem.SqlServerDialect
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.koin.ksp.generated.module
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.readText

object GenerateSqlServerScript : Spek({

    Feature("Can generate a sql server script") {

        Scenario("Apply to sql script") {
            val tempDir by memoized(factory = {
                Files.createTempDirectory("temp")
            }) { it.toFile().deleteRecursively() }
            val result by memoized {
                kontrolDb {
                    dbSettings {
                        defaultSchema("dbo")
                        jdbcUrl("jdbc:hsqldb:mem:testdb;sql.syntax_mss=true")
                    }
                    dbDialect(SqlServerDialect())
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
    }
})

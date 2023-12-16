package net.futureset.kontroldb

import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.koin.ksp.generated.module
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

private const val EXISTING_DB = "jdbc:sqlserver://localhost:1433;trustServerCertificate=true;databaseName=TEST_DB"

class ManualPetstoreTest {

    @BeforeEach
    fun setup() {
        // suggest creating the database manually in your server with a deployment user
        //  CREATE DATABASE [TEST_DB];",
        //  CREATE LOGIN [deploymentUser] WITH PASSWORD = 'APasswordForTesting123', DEFAULT_DATABASE=[TEST_DB]",
        //  ALTER AUTHORIZATION ON DATABASE::[TEST_DB] TO [deploymentUser]
    }

    @Test
    @Disabled("Enable to generate a script")
    fun `can generate a sql script of Petstore changes from the current database`() {
        val sqlOutputDir = Paths.get("build")
        KontrolDbEngineBuilder.dsl {
            dbSettings {
                jdbcUrl(EXISTING_DB)
                username("deploymentUser")
                password("APasswordForTesting123")
            }
            changeModules(PetStore().module)
        }.use { engine ->

            val outputSqlFile = sqlOutputDir.resolve("output.sql")

            engine.generateSql(sqlOutputDir)

            assertThat(outputSqlFile).exists()
            println(outputSqlFile.toStringWithNumberedLines())
            println("Script file in ${outputSqlFile.absolutePathString()}")
        }
    }

    @Test
    @Disabled("Enable to execute changes against the database ")
    fun `can apply Petstore changes directly to the database`() {
        KontrolDbEngineBuilder.dsl {
            dbSettings {
                jdbcUrl(EXISTING_DB)
                username("deploymentUser")
                password("APasswordForTesting123")
            }
            changeModules(PetStore().module)
        }.use {
            it.applySql()
        }
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.koin.ksp.generated.module
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.path.absolutePathString

@ExtendWith(DatabaseProvision::class)
internal class PetStoreTest {

    @Test
    fun `can generate a sql script and then execute it on empty db`(@TempDir sqlOutputDir: Path, @DialectName dialect: String) {
        KontrolDbEngineBuilder.dsl {
            loadConfig("test-config.yml")
            changeModules(PetStore().module)
        }.use { engine ->

            val outputSqlFile = sqlOutputDir.resolve("output.sql")

            engine.generateSql(sqlOutputDir)

            assertThat(outputSqlFile).exists()

            engine.applySqlDirectly.close()

            println(outputSqlFile.toStringWithNumberedLines())

            when (dialect) {
                "hsqldb" -> engine.applySqlDirectly.withConnection {
                    engine.effectiveSettings.runScriptAgainstDb(it, outputSqlFile)
                }
                "sqlserver" -> {
                    val destOutputSqlFile = Paths.get(System.getProperty("shareddir", "build"), "output.sql")
                    println("${destOutputSqlFile.absolutePathString()}")
                    Files.createDirectories(destOutputSqlFile.parent)
                    Files.copy(outputSqlFile, destOutputSqlFile, StandardCopyOption.REPLACE_EXISTING)
                    engine.effectiveSettings.run {
                        assertThat(
                            (
                                "docker exec -i kontrol-sqlserver /opt/mssql-tools/bin/sqlcmd " +
                                    "-U $username -P $password -e -H localhost -C -i /var/outputdir/output.sql"
                                )
                                .executeAsShell(),
                        ).isZero()
                    }
                }
                else -> throw UnsupportedOperationException("Unsupported dialect $dialect")
            }
        }
    }

    @Test
    fun `can apply changes directly to the database`() {
        KontrolDbEngineBuilder.dsl {
            loadConfig("test-config.yml")
            changeModules(PetStore().module)
        }.use {
            assertThat(it.getAppliedRefactorings())
                .describedAs("No changes have been applied yet").isEmpty()
            assertThat(it.applySql())
                .describedAs("At least two SQLs were executed").isGreaterThan(2)
            val applied = it.getAppliedRefactorings()
            assertThat(applied)
                .describedAs("Changes are reported as applied").hasSizeGreaterThan(2)
            println(applied.toList().joinToString(separator = "\n"))
            assertThat(it.applySql())
                .describedAs("Re-running will apply no changes because its up to date").isZero()
        }
    }
}

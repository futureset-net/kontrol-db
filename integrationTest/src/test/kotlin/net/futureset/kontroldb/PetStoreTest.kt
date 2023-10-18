package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.koin.ksp.generated.module
import java.nio.file.Path
import kotlin.io.path.readLines

internal class PetStoreTest {

    @Test
    fun `Can generate a sql script and then execute it on empty db`(@TempDir sqlOutputDir: Path) {
        dsl {
            loadConfig("test-config.yml")
            changeModules(PetStore().module)
        }.use { engine ->

            val outputSqlFile = sqlOutputDir.resolve("output.sql")

            engine.generateSql(sqlOutputDir)

            assertThat(outputSqlFile).exists()

            engine.sqlExecutor.close()

            val readText = outputSqlFile.readLines()
            println(readText.mapIndexed { index, s -> "$index".padStart(4, '0') + " $s" }.joinToString(separator = "\n"))
            engine.sqlExecutor.withConnection {
                engine.effectiveSettings.runScriptAgainstDb(it, outputSqlFile)
            }
        }
    }

    @Test
    fun `Can apply changes directly to the database`() {
        dsl {
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

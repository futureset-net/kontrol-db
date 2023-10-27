package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.koin.ksp.generated.module
import java.nio.file.Path
import kotlin.io.path.readText

@ExtendWith(DatabaseProvision::class)
class GenerateARollbackScriptTest {

    @Test
    fun `Can generate a rollback script`(@TempDir tempDir: Path) {
        dsl {
            dialect("default") // will be overridden by loadConfig, but included for coverage
            loadConfig("test-config.yml")
            executionSettings {
                isOutputTablespace(true)
                isOutputCatalog(true)
                isOutputSchema(true)
            }
            dbSettings {
                defaultSchema("dbo")
                defaultTablespace("MY_TABLESPACE")
                defaultIndexTablespace("MY_INDEX_TABLESPACE")
            }
            changeModules(PetStore().module)
        }.use { result ->
            val generatedFile = tempDir.resolve("output.sql")
            result.generateSqlRollback(tempDir)

            assertThat(generatedFile).describedAs("script was generated").exists()
            val scriptContent = generatedFile.readText()
            println(scriptContent)
            assertThat(scriptContent).contains("DROP TABLE ${result.effectiveSettings.openQuote}KONTROL_DB_VERSIONING${result.effectiveSettings.closeQuote}")
            assertThat(scriptContent)
                .contains("DROP TABLE ${result.effectiveSettings.openQuote}CUSTOMER${result.effectiveSettings.closeQuote}")
        }
    }
}

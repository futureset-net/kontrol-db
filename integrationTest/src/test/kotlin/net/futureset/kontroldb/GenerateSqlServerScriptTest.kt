package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.targetsystem.SqlServerDialect
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.koin.ksp.generated.module
import java.nio.file.Path
import kotlin.io.path.readText

internal class GenerateSqlServerScriptTest {

    @Test
    fun `Can generate a sql server script`(@TempDir tempDir: Path) {
        val result =
            kontrolDb {
                executionSettings {
                    outputTablespace = true
                    outputCatalog = true
                    outputSchema = true
                }
                dbSettings {
                    defaultSchema("dbo")
                    jdbcUrl("jdbc:hsqldb:mem:testdb;sql.syntax_mss=true")
                    defaultTablespace("MY_TABLESPACE")
                    defaultIndexTablespace("MY_INDEX_TABLESPACE")
                }
                dbDialect(SqlServerDialect())
                changeModules(PetStore().module)
            }

        var generatedFile = tempDir.resolve("output.sql")
        result.generateSql(generatedFile)

        assertThat(generatedFile).describedAs("script was generated").exists()
        val scriptContent = generatedFile.readText()
        println(scriptContent)
        assertThat(scriptContent).contains("CREATE TABLE [KONTROL_DB_VERSIONING]")
        assertThat(scriptContent).describedAs("SQL SERVER has different syntax for adding not null constraint")
            .contains("ALTER TABLE [CUSTOMER] ALTER COLUMN [LASTNAME] VARCHAR(25) NOT NULL")
    }
}

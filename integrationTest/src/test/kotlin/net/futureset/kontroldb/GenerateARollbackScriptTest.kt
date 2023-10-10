package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDb.Companion.dsl
import net.futureset.kontroldb.targetsystem.SqlServerDialect
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.koin.ksp.generated.module
import java.nio.file.Path
import kotlin.io.path.readText

class GenerateARollbackScriptTest {

    @Test
    fun `Can generate a sql server rollback script`(@TempDir tempDir: Path) {
        dsl {
            executionSettings {
                isOutputTablespace(true)
                isOutputCatalog(true)
                isOutputSchema(true)
            }
            dbSettings {
                defaultSchema("dbo")
                jdbcUrl("jdbc:hsqldb:mem:testdb;sql.syntax_mss=true")
                defaultTablespace("MY_TABLESPACE")
                defaultIndexTablespace("MY_INDEX_TABLESPACE")
            }
            dbDialect(SqlServerDialect())
            changeModules(PetStore().module)
        }.use { result ->

            val generatedFile = tempDir.resolve("output.sql")
            result.generateSqlRollback(tempDir)

            assertThat(generatedFile).describedAs("script was generated").exists()
            val scriptContent = generatedFile.readText()
            println(scriptContent)
            assertThat(scriptContent).contains("DROP TABLE [KONTROL_DB_VERSIONING]")
            assertThat(scriptContent)
                .contains("DROP TABLE [CUSTOMER]")
        }
    }
}

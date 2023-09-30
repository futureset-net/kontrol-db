package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDb.Companion.dsl
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.koin.ksp.generated.module
import java.nio.file.Path
import kotlin.io.path.readText

internal class ApplyToHsqlDbTest {

    @Test
    fun `Can generate an sql script that will run on HsqlDb`(@TempDir sqlOutputDir: Path) {
        val engine = dsl {
            changeModules(PetStore().module)
        }

        val outputSqlFile = sqlOutputDir.resolve("output.sql")

        engine.generateSql(sqlOutputDir)

        assertThat(outputSqlFile).exists()

        val readText = outputSqlFile.readText()
        println(readText)
        assertThat(readText).isNotEmpty()
    }

    @Test
    fun `Can apply changes directly to the database`() {
        dsl {
            changeModules(PetStore().module)
        }.run {
            assertThat(getCurrentState())
                .describedAs("No changes have been applied yet").isEmpty()
            assertThat(applySql())
                .describedAs("At least two SQLs were executed").isGreaterThan(2)
            assertThat(getCurrentState())
                .describedAs("Changes are reported as applied").hasSizeGreaterThan(2)
            println(getCurrentState().toList().joinToString(separator = "\n"))
            assertThat(applySql())
                .describedAs("Re-running will apply no changes because its up to date").isZero()
        }
    }
}

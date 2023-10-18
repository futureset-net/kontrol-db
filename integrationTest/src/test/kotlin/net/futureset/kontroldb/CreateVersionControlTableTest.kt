package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.readText

internal class CreateVersionControlTableTest {

    @Test
    fun `Create a version control table`() {
        dsl {
        }.use { result ->
            assertThat(result).isNotNull
            assertThat(result.refactorings).hasSize(1)
            assertThat(result.refactorings.first()).isInstanceOf(CreateVersionControlTable::class.java)
            assertThat(result.refactorings.first() as CreateVersionControlTable)
                .extracting { x -> x.forward.first() as CreateTable }
                .extracting(CreateTable::table)
                .extracting(SchemaObject::name)
                .extracting(DbIdentifier::name)
                .isEqualTo(DEFAULT_VERSION_CONTROL_TABLE)
        }
    }

    @Test
    fun `Check has a version control table with custom name`(@TempDir tempDir: Path) {
        val customName = "HELLO"
        dsl {
            dbSettings {
                versionControlTable {
                    name(customName)
                    catalogAndSchema("PUBLIC", "PUBLIC")
                }
                defaultTablespace("TEST_TABLESPACE")
            }
        }.use { result ->

            assertThat(result).isNotNull

            assertThat(result.refactorings).hasSize(1)
            assertThat(result.refactorings.first()).isInstanceOf(CreateVersionControlTable::class.java)
            assertThat(result.refactorings.first() as CreateVersionControlTable)
                .extracting { x -> x.forward.first() as CreateTable }
                .extracting(CreateTable::table)
                .extracting(SchemaObject::name)
                .extracting(DbIdentifier::name)
                .isEqualTo(customName)

            val script = tempDir.resolve("output.sql")
            result.generateSql(tempDir)
            assertThat(script).exists()
            val scriptText = script.readText()
            println(scriptText)
            assertThat(scriptText).contains("""CREATE TABLE "PUBLIC"."PUBLIC"."HELLO"""")
        }
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.test.petstore.CreateProductTable
import net.futureset.kontroldb.test.petstore.IncrementCustomerId
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.koin.ksp.generated.module

internal class VariousCheckSumScenariosTest {

    @Test
    fun `Refactorings can be re-run where indicated when the checksum changes`() {
        val result = kontrolDb {
            changeModules(PetStore().module)
        }

        assertThat(result.getCurrentState()).isEmpty()

        assertThat(result.applySql()).describedAs("Migration is created").isGreaterThan(2)

        assertThat(result.getCurrentState()).describedAs("changes applied").hasSizeGreaterThan(2)

        result.sqlExecutor.withConnection {
            it.executeSql("UPDATE $DEFAULT_VERSION_CONTROL_TABLE SET CHECK_SUM='INVALID' WHERE ID='${IncrementCustomerId::class.qualifiedName}'")
        }

        assertThat(result.applySql()).isGreaterThan(1)
        assertThat(
            result.sqlExecutor.withConnection { conn ->
                conn.executeQuery("SELECT EXECUTION_COUNT FROM $DEFAULT_VERSION_CONTROL_TABLE WHERE ID='${IncrementCustomerId::class.qualifiedName}'") {
                    it.getInt(1)
                }.first()
            },
        ).isEqualTo(2)
    }

    @Test
    fun `If checksum changes on a non re-runnable changeset, this is an error`() {
        val result = kontrolDb {
            changeModules(PetStore().module)
        }

        assertThat(result.getCurrentState()).isEmpty()

        assertThat(result.applySql()).describedAs("Migration is created").isGreaterThan(2)

        assertThat(result.getCurrentState()).describedAs("changes applied").hasSizeGreaterThan(2)

        result.sqlExecutor.withConnection {
            it.executeSql("UPDATE $DEFAULT_VERSION_CONTROL_TABLE SET CHECK_SUM='INVALID' WHERE ID='${CreateProductTable::class.qualifiedName}'")
        }

        assertThatThrownBy(result::applySql).isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Checksum mismatch for net.futureset.kontroldb.test.petstore.CreateProductTable ")
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.ColumnValue.Companion.column
import net.futureset.kontroldb.ColumnValue.Companion.expression
import net.futureset.kontroldb.ExecuteMode.ALWAYS
import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.modelchange.executeQuery
import net.futureset.kontroldb.modelchange.executeSql
import net.futureset.kontroldb.modelchange.insert
import net.futureset.kontroldb.modelchange.update
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.test.petstore.CreateProductTable
import net.futureset.kontroldb.test.petstore.IncrementCustomerId
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ksp.generated.module
import java.time.LocalDateTime

internal class VariousCheckSumScenariosTest {

    @Test
    fun `Refactorings can be re-run where indicated when the checksum changes`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(PetStore().module)
        }.use { result ->

            assertThat(result.getAppliedRefactorings()).isEmpty()

            assertThat(result.applySql()).describedAs("Migration is created").isGreaterThan(2)

            assertThat(result.getAppliedRefactorings()).describedAs("changes applied").hasSizeGreaterThan(2)

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
    }

    @Test
    fun `If checksum changes on a non re-runnable changeset, this is an error`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(PetStore().module)
        }.use { result ->

            assertThat(result.getAppliedRefactorings()).isEmpty()

            assertThat(result.applySql()).describedAs("Migration is created").isGreaterThan(2)

            assertThat(result.getAppliedRefactorings()).describedAs("changes applied").hasSizeGreaterThan(2)

            result.sqlExecutor.withConnection {
                it.executeSql("UPDATE $DEFAULT_VERSION_CONTROL_TABLE SET CHECK_SUM='INVALID' WHERE ID='${CreateProductTable::class.qualifiedName}'")
            }

            assertThatThrownBy(result::applySql).isInstanceOf(IllegalStateException::class.java)
                .hasMessageContaining("Checksum mismatch for net.futureset.kontroldb.test.petstore.CreateProductTable ")
        }
    }

    class InsertIntoProduct : Refactoring(
        executionOrder { ymd(2023, 9, 10) author("ben") },
        forward = changes {
            insert {
                table("PRODUCT")
                values {
                    value("ID", 1)
                    value("PRODUCT_NAME", "PRODUCT NAME")
                    value("PACKAGE_ID", System.currentTimeMillis() % 1000)
                    value("CURRENT_INVENTORY_COUNT", 1)
                    value("STORE_COST", 1.50f)
                    value("SALE_PRICE", 1.30f)
                    value("LAST_UPDATE_DATE", LocalDateTime.now())
                    value("UPDATED_BY_USER", "me")
                    value("PET_FLAG", true)
                }
            }
        },
        rollback = listOf(),
    )

    class IncrementInventory : Refactoring(
        executionOrder { ymd(2023, 9, 10) author("ben") sequence(2) },
        executeMode = ALWAYS,
        forward = changes {
            update {
                table("PRODUCT")
                set("CURRENT_INVENTORY_COUNT" to expression("CURRENT_INVENTORY_COUNT+1"))
                where {
                    column("ID") eq 1
                }
            }
        },
        rollback = listOf(),
    )

    @Test
    fun `A run always refactoring always runs!`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateProductTable).bind(Refactoring::class)
                    singleOf(::InsertIntoProduct).bind(Refactoring::class)
                    singleOf(::IncrementInventory).bind(Refactoring::class)
                },
            )
        }.use { result ->
            assertThat(result.refactorings.last()).isInstanceOf(IncrementInventory::class.java)
            assertThat(result.refactorings.last()).extracting(Refactoring::executeMode).isEqualTo(ALWAYS)

            assertThat(result.applySql()).isGreaterThan(2)

            assertThat(result.getNextRefactorings()).hasSize(1)
            assertThat(result.applySql()).isEqualTo(4)
            assertThat(result.getNextRefactorings()).hasSize(1)
            assertThat(result.applySql()).isEqualTo(4)
        }
    }
}

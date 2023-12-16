package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.model.ColumnValue.Companion.column
import net.futureset.kontroldb.model.ColumnValue.Companion.expression
import net.futureset.kontroldb.modelchange.insertRowsInto
import net.futureset.kontroldb.modelchange.updateRowsOf
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.refactoring.ExecuteMode.ALWAYS
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.test.petstore.CreateProductTable
import net.futureset.kontroldb.test.petstore.IncrementCustomerId
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ksp.generated.module
import java.time.LocalDateTime

@ExtendWith(DatabaseProvision::class)
internal class VariousCheckSumScenariosTest {

    @Test
    fun `Refactorings can be re-run where indicated when the checksum changes`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(PetStore().module)
        }.use { result ->
            assertThat(result.getAppliedRefactorings()).isEmpty()

            assertThat(result.applySql()).describedAs("Migration is created").isGreaterThan(2)

            assertThat(result.getAppliedRefactorings().simpleNames()).describedAs("changes applied")
                .containsExactly(
                    "CreateVersionControlTable",
                    "StartMigration",
                    "CreateCustomerTable",
                    "CreateProductTable",
                    "InsertACustomer",
                    "IncrementCustomerId",
                    "CreateCustomerSaleTable",
                    "CreateTableByMistake",
                    "DropMistakeTable",
                    "CreateSalesItemTable",
                    "EndMigration",
                )

            result.applySqlDirectly.withConnection {
                it.executeSql(
                    result.effectiveSettings
                        .run { "UPDATE ${quote(DEFAULT_VERSION_CONTROL_TABLE)} SET ${quote("CHECK_SUM")}='INVALID' WHERE ${quote("ID")}='${IncrementCustomerId::class.qualifiedName}'" },
                )
            }

            assertThat(result.applySql()).isGreaterThan(1)
            assertThat(
                result.applySqlDirectly.withConnection { conn ->
                    conn.executeQuery(
                        result.effectiveSettings
                            .run { "SELECT ${quote("EXECUTION_COUNT")} FROM ${quote(DEFAULT_VERSION_CONTROL_TABLE)} WHERE ${quote("ID")}='${IncrementCustomerId::class.qualifiedName}'" },
                    ) {
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

            result.applySqlDirectly.withConnection {
                it.executeSql(
                    result.effectiveSettings
                        .run { "UPDATE ${quote(DEFAULT_VERSION_CONTROL_TABLE)} SET ${quote("CHECK_SUM")}='INVALID' WHERE ${quote("ID")}='${CreateProductTable::class.qualifiedName}'" },
                )
            }

            assertThatThrownBy(result::applySql).isInstanceOf(IllegalStateException::class.java)
                .hasMessageContaining("Checksum mismatch for net.futureset.kontroldb.test.petstore.CreateProductTable ")
        }
    }

    class InsertIntoProduct : Refactoring(
        executionOrder { ymd(2023, 9, 10) author("ben") },
        forward = changes {
            insertRowsInto("PRODUCT") {
                row {
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
                tableWithAlias("PRODUCT", "A")
            }
        },
        rollback = listOf(),
    )

    class IncrementInventory : Refactoring(
        executionOrder { ymd(2023, 9, 10) author("ben") sequence(2) },
        executeMode = ALWAYS,
        forward = changes {
            updateRowsOf("PRODUCT") {
                set("CURRENT_INVENTORY_COUNT" to expression("\"CURRENT_INVENTORY_COUNT\"+1"))
                tableWithAlias("PRODUCT", "B")
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

            assertThat(result.getNextRefactorings().simpleNames()).containsExactly("IncrementInventory")
            assertThat(result.applySql()).isGreaterThanOrEqualTo(4)
            assertThat(result.getNextRefactorings().simpleNames()).containsExactly("IncrementInventory")
            assertThat(result.applySql()).isGreaterThanOrEqualTo(4)
        }
    }
}

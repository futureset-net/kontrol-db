package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.modelchange.createView
import net.futureset.kontroldb.modelchange.dropViewIfExists
import net.futureset.kontroldb.refactoring.ExecuteMode
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class CreateViewTest {

    @Test
    fun `Can apply a view and select from it`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAView).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("Create view").isGreaterThanOrEqualTo(10)
            it.applySqlDirectly.withConnection { conn ->
                conn.executeQuery("SELECT * FROM MY_VIEW") { rs ->
                    while (rs.next()) {
                        println(rs.getString(1))
                    }
                }
            }
        }
    }

    @Test
    fun `Can apply a view from a file and select from it`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAViewFromClasspathResource).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("create view").isGreaterThanOrEqualTo(11)
            it.applySqlDirectly.withConnection { conn ->
                conn.executeQuery("SELECT * FROM MY_VIEW") { rs ->
                    while (rs.next()) {
                        println(rs.getString(1))
                    }
                }
            }
        }
    }

    @Test
    fun `Can apply a view from a file and select from it, not specifying CREATE or ALTER`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAViewFromClasspathResourceWithoutWholeDefinition).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("create view").isGreaterThanOrEqualTo(10)
            it.applySqlDirectly.withConnection { conn ->
                conn.executeQuery("SELECT * FROM MY_VIEW") { rs ->
                    while (rs.next()) {
                        println(rs.getString(1))
                    }
                }
            }
        }
    }
    class CreateAViewFromClasspathResource : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
            dropViewIfExists("NEW_CUSTOMER")
            createView("NEW_CUSTOMER") {
                resource("net/futureset/kontroldb/NewCustomerView.sql")
                wholeDefinition(true)
                language("SQL")
            }
        },
        rollback = emptyList(),
        executeMode = ExecuteMode.ON_CHANGE,
    )

    class CreateAViewFromClasspathResourceWithoutWholeDefinition : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
            createView("NEW_CUSTOMER") {
                body(
                    Thread.currentThread().contextClassLoader.getResource("net/futureset/kontroldb/NewCustomerView.sql")!!.readText().replace("CREATE ", ""),
                )
                wholeDefinition(false)
                language("SQL")
            }
        },
        rollback = emptyList(),
        executeMode = ExecuteMode.ON_CHANGE,
    )

    class CreateAView : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
            createProcedure("NEW_CUSTOMER") {
                body(
                    Thread.currentThread().contextClassLoader.getResource("net/futureset/kontroldb/NewCustomerView.sql")!!.readText(),
                )
                wholeDefinition(true)
            }
        },
        rollback = emptyList(),

    )
}

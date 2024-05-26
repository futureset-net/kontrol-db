package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.modelchange.dropProcedureIfExists
import net.futureset.kontroldb.refactoring.ExecuteMode
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.samples.CreateAProcedure
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.nio.file.Path
import kotlin.io.path.writeText

class CreateAProcedurePartialDefinition : Refactoring(
    executionOrder {
        ymd(2023, 11, 30)
        author("ben")
    },
    forward = changes {
        createProcedure("NEW_CUSTOMER") {
            body(
                Thread.currentThread().contextClassLoader.getResource("net/futureset/kontroldb/NewCustomerProc.sql")!!.readText().replace("CREATE ", ""),
            )
            wholeDefinition(false)
        }
    },
    rollback = emptyList(),

)

@ExtendWith(DatabaseProvision::class)
internal class StoredProcTest {

    @Test
    fun `Can apply a stored proc and execute it`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedure).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("Run procs").isGreaterThanOrEqualTo(10)
            it.applySqlDirectly.withConnection { conn ->
                conn.executeSql(it.effectiveSettings.run { "{ call ${quote("NEW_CUSTOMER")}('BEN','SMITH','ADR') }" })
            }
        }
    }

    @Test
    fun `Can apply a stored proc and execute it with partial definition`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedurePartialDefinition).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("Run procs").isGreaterThanOrEqualTo(10)
            it.applySqlDirectly.withConnection { conn ->
                conn.executeSql(it.effectiveSettings.run { "{call ${quote("NEW_CUSTOMER")}('BEN','SMITH','ADR')}" })
            }
        }
    }

    class CreateAProcedureFromClasspathResource : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
            dropProcedureIfExists("NEW_CUSTOMER")
            createProcedure("NEW_CUSTOMER") {
                resource("net/futureset/kontroldb/NewCustomerProc.sql")
                wholeDefinition(true)
                language("SQL")
            }
        },
        rollback = emptyList(),
        executeMode = ExecuteMode.ON_CHANGE,
    )

    class CreateAProcedureFromFile : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
            dropProcedureIfExists("NEW_CUSTOMER")
            createProcedure("NEW_CUSTOMER") {
                resource("NewCustomerProc.sql")
                wholeDefinition(true)
                language("SQL")
            }
        },
        rollback = emptyList(),
        executeMode = ExecuteMode.ON_CHANGE,
    )

    @Test
    fun `Can apply a stored proc from a file and execute it`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedureFromClasspathResource).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("Run procs").isGreaterThanOrEqualTo(11)
            it.applySqlDirectly.withConnection { conn ->
                conn.executeSql(it.effectiveSettings.run { "{ call ${quote("NEW_CUSTOMER")}('BEN','SMITH','ADR') }" })
            }
        }
    }

    @Test
    fun `Can apply a stored proc from a file and re-execute if it changes`(@TempDir workingDir: Path) {
        val procFile = workingDir.resolve("NewCustomerProc.sql")
        procFile.writeText(javaClass.getResource("/net/futureset/kontroldb/NewCustomerProc.sql")!!.readText())

        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                externalFileRoot(workingDir)
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedureFromFile).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("Run procs").isGreaterThanOrEqualTo(11)
            assertThat(it.applySql()).describedAs("No changes should be re-run").isZero()
        }

        procFile.replaceText("LDN", "XYZ")

        dsl {
            loadConfig("test-config.yml")
            executionSettings {
                externalFileRoot(workingDir)
            }
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedureFromFile).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.getNextRefactorings()).hasAtLeastOneElementOfType(CreateAProcedureFromFile::class.java)
            assertThat(it.getNextRefactorings()).noneMatch { it is CreateCustomerTable }
            assertThat(it.applySql()).describedAs("Should detect change").isGreaterThanOrEqualTo(5)
        }
    }
}

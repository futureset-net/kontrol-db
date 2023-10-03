package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDb.Companion.dsl
import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.modelchange.dropProcedureIfExists
import net.futureset.kontroldb.targetsystem.HsqlDbDialect
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.io.File

class CreateAProcedure : Refactoring(
    executionOrder {
        ymd(2023, 11, 30)
        author("ben")
    },
    forward = changes {
        createProcedure {
            procedure {
                name("NEW_CUSTOMER")
            }
            body(
                """ 
            CREATE PROCEDURE NEW_CUSTOMER( IN firstname VARCHAR(50), IN lastname VARCHAR(50), IN address VARCHAR(100))
            MODIFIES SQL DATA
            BEGIN ATOMIC
                INSERT INTO CUSTOMER(CUST_ID,FIRSTNAME,LASTNAME,ADDRESS,CITY,STATE,ZIP) 
                VALUES (1, firstname, lastname, address, 'LDN', 'NY', '123');  
            END
                """.trimIndent(),
            )
            wholeDefinition(true)
        }
    },
    rollback = emptyList(),

)

internal class StoredProcTest {

    @Test
    fun `Can apply a stored proc and execute it`() {
        dsl {
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedure).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql())
                .describedAs("Run procs").isEqualTo(8)
            it.sqlExecutor.withConnection { conn ->
                conn.executeSql("call NEW_CUSTOMER('BEN','SMITH','ADR')")
            }
        }
    }

    class CreateAProcedureFromAFile : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
            dropProcedureIfExists {
                name("NEW_CUSTOMER")
            }
            createProcedure {
                procedure {
                    name("NEW_CUSTOMER")
                }
                resource("net/futureset/kontroldb/NewCustomerProc.sql")
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
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedureFromAFile).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql())
                .describedAs("Run procs").isEqualTo(9)
            it.sqlExecutor.withConnection { conn ->
                conn.executeSql("call NEW_CUSTOMER('BEN','SMITH','ADR')")
            }
        }
    }

    @Test
    fun `Can apply a stored proc from a file and re-execute if it changes`() {
        dsl {
            dbDialect(NonClosingDialect(HsqlDbDialect()))
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateAProcedureFromAFile).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).describedAs("Run procs").isEqualTo(9)
            assertThat(it.applySql()).describedAs("No changes should be re-run").isZero()
        }
        val procDef =
            requireNotNull(javaClass.getResource("/net/futureset/kontroldb/NewCustomerProc.sql")?.file?.let(::File))
        try {
            procDef.replaceText("LDN", "XYZ")

            dsl {
                changeModules(
                    module {
                        singleOf(::CreateCustomerTable).bind(Refactoring::class)
                        singleOf(::CreateAProcedureFromAFile).bind(Refactoring::class)
                    },
                )
            }.use {
                assertThat(it.getNextRefactorings()).hasAtLeastOneElementOfType(CreateAProcedureFromAFile::class.java)
                assertThat(it.getNextRefactorings()).noneMatch { it is CreateCustomerTable }
                assertThat(it.applySql())
                    .describedAs("Should detect change").isEqualTo(3)
            }
        } finally {
            procDef.replaceText("XYZ", "LDN")
        }
    }

    fun File.replaceText(search: String, replace: String) {
        val def = this.readText()
        val newDef = def.replace(search, replace)
        this.writeText(newDef)
    }
}

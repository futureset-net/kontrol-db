package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDb.Companion.dsl
import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

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
        }.run {
            assertThat(applySql())
                .describedAs("Run procs").isEqualTo(8)
            sqlExecutor.withConnection {
                it.executeSql("call NEW_CUSTOMER('BEN','SMITH','ADR')")
            }
        }
    }

    class CreateAProcedureFromAFile : Refactoring(
        executionOrder {
            ymd(2023, 11, 30)
            author("ben")
        },
        forward = changes {
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
        }.run {
            assertThat(applySql())
                .describedAs("Run procs").isEqualTo(8)
            sqlExecutor.withConnection {
                it.executeSql("call NEW_CUSTOMER('BEN','SMITH','ADR')")
            }
        }
    }
}

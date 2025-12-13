package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.model.StandardColumnTypes
import net.futureset.kontroldb.modelchange.createIndex
import net.futureset.kontroldb.modelchange.createSequence
import net.futureset.kontroldb.modelchange.createView
import net.futureset.kontroldb.modelchange.dropIndexIfExists
import net.futureset.kontroldb.modelchange.dropSequenceIfExists
import net.futureset.kontroldb.modelchange.dropTableIfExists
import net.futureset.kontroldb.modelchange.dropViewIfExists
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class DropIfExistsTest {
    class CreateACustomerIndexAndDropEverything(
        schemaName: String,
    ) : Refactoring(
        executionOrder {
            author("ben")
            ymd(2023, 10, 1)
        },
        forward =
        changes {
            createIndex("IX_LASTNAME") {
                column("LASTNAME")
                table("CUSTOMER")
            }
            createView("MY_VIEW") {
                body("CREATE VIEW \"MY_VIEW\" AS SELECT \"LASTNAME\" FROM \"CUSTOMER\"")
                wholeDefinition(true)
            }
            createSequence("MY_SEQUENCE") {
                cache(10)
                minValue(1)
                maxValue(100)
                incrementBy(2)
                startWith(3)
                columnType(StandardColumnTypes.INT64)
                cycle()
                otherSchema {
                    schema(schemaName)
                }
            }
            dropIndexIfExists("IX_LASTNAME") {
                table("CUSTOMER")
                index {
                    schema(schemaName)
                }
            }
            dropSequenceIfExists("MY_SEQUENCE")
            dropViewIfExists("MY_VIEW")
            dropTableIfExists("CUSTOMER")
            dropTableIfExists("NON_EXISTENT")
        },
        rollback = emptyList(),
    )

    @Test
    fun `Can drop a table and index if they exist`(
        @DialectName dialectName: String,
    ) {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    refactoring(::CreateCustomerTable)
                    single {
                        CreateACustomerIndexAndDropEverything(
                            when (dialectName) {
                                "sqlserver" -> "dbo"
                                "postgres" -> "public"
                                else -> "PUBLIC"
                            },
                        )
                    }.bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).isGreaterThan(1)
        }
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.addColumnsTo
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropColumnsFrom
import net.futureset.kontroldb.refactoring.Refactoring
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
class AddAndDropColumnsTest {

    @Test
    fun `add a column and drop it`(@DialectName dialectName: String) {
        KontrolDbEngineBuilder.dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    refactoring(::CreateATable)
                    single {
                        AddAColumn(
                            when (dialectName) {
                                "sqlserver" -> "dbo"
                                "postgres" -> "public"
                                else -> "PUBLIC" },
                        )
                    }.bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).isGreaterThan(1)
            assertThat(
                it.applySqlDirectly.withConnection { conn ->
                    val quote = { s: String -> it.effectiveSettings.openQuote + s + it.effectiveSettings.closeQuote }
                    conn.executeQuery("SELECT ${quote("ONE_COLUMN")},${quote("TWO_COLUMN")},${quote("THREE_COLUMN")} FROM ${quote("FRED")}") {
                        ""
                    }
                },
            ).isEmpty()
        }
    }

    class CreateATable : Refactoring(
        executionOrder {
            ymd(2023, 11, 23)
            author("ben")
        },
        forward = changes {
            createTable("FRED") {
                column("ONE_COLUMN", INT_32)
            }
        },
        rollback = emptyList(),
    )

    class AddAColumn(schemaName: String) : Refactoring(
        executionOrder {
            ymd(2023, 11, 24)
            author("ben")
            sequence(2)
        },
        forward = changes {
            addColumnsTo("FRED") {
                column("TWO_COLUMN", INT_32)
                column("THREE_COLUMN", INT_32)
                column("FOUR_COLUMN", INT_32)
                column("FIVE_COLUMN", INT_32)
                table {
                    schema(schemaName)
                }
            }
            dropColumnsFrom("FRED") {
                column("FOUR_COLUMN")
                column("FIVE_COLUMN")
                table {
                    schema(schemaName)
                }
            }
        },
        rollback = emptyList(),
    )
}

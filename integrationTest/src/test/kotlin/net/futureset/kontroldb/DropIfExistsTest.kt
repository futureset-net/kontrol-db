package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.modelchange.createIndex
import net.futureset.kontroldb.modelchange.dropIndexIfExists
import net.futureset.kontroldb.modelchange.dropTableIfExists
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class DropIfExistsTest {

    class CreateACustomerIndexAndDropEverything : Refactoring(
        executionOrder {
            author("ben")
            ymd(2023, 10, 1)
        },
        forward = changes {
            createIndex {
                indexName("IX_LASTNAME")
                column("LASTNAME")
                table("CUSTOMER")
            }
            dropIndexIfExists {
                index("IX_LASTNAME")
                table("CUSTOMER")
            }
            dropTableIfExists {
                name("CUSTOMER")
            }
            dropTableIfExists {
                name("NON_EXISTENT")
            }
        },
        rollback = emptyList(),
    )

    @Test
    fun `Can drop a table and index if they exist`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateCustomerTable).bind(Refactoring::class)
                    singleOf(::CreateACustomerIndexAndDropEverything).bind(Refactoring::class)
                },
            )
        }.use {
            assertThat(it.applySql()).isGreaterThan(1)
        }
    }
}

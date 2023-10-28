package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.modelchange.createRole
import net.futureset.kontroldb.modelchange.dropRole
import net.futureset.kontroldb.modelchange.grantPermissions
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class CreateARoleAndGrantSomePermissionsTest {

    class CreateARoleCalledFred : Refactoring(
        executionOrder {
            ymd(2023, 9, 13)
            author("ben")
        },
        forward = changes {
            createRole {
                roleName("FRED")
            }
            grantPermissions {
                on("KONTROL_DB_VERSIONING") {
                }
                objectType(DbObjectType.TABLE)
                permissions("INSERT", "UPDATE", "DELETE")
                to("FRED")
            }
            grantPermissions {
                on(SchemaObject(DbIdentifier(DEFAULT_VERSION_CONTROL_TABLE)))
                objectType("TABLE")
                permissions("SELECT")
                to("FRED")
            }
        },
        rollback = emptyList(),
    )

    class DropTheRoleAgain : Refactoring(
        executionOrder {
            ymd(2023, 9, 14)
            author("ben")
        },
        forward = changes {
            dropRole {
                roleName("FRED")
            }
        },
        rollback = emptyList(),
    )

    @Test
    fun `can create a role and grant permissions to it`() {
        val result = dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(::CreateARoleCalledFred).bind(Refactoring::class)
                    singleOf(::DropTheRoleAgain).bind(Refactoring::class)
                },
            )
        }
        result.use { it.applySql() }
    }
}

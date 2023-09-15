package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.changes
import net.futureset.kontroldb.KontrolDbDsl.Companion.executionOrder
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import org.junit.jupiter.api.Test
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

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
                on(DEFAULT_VERSION_CONTROL_TABLE)
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
        val result = KontrolDbDsl.kontrolDb {
            changeModules(
                module {
                    singleOf(::CreateARoleCalledFred).bind(Refactoring::class)
                    singleOf(::DropTheRoleAgain).bind(Refactoring::class)
                },
            )
        }
        result.applySql()
    }
}

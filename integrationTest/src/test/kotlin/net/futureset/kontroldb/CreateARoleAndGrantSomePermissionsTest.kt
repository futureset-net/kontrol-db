package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.modelchange.createRole
import net.futureset.kontroldb.modelchange.dropRole
import net.futureset.kontroldb.modelchange.grantPermissions
import net.futureset.kontroldb.modelchange.revokePermissions
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.refactoring.Refactoring
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class CreateARoleAndGrantSomePermissionsTest {

    class CreateARoleCalledFred : Refactoring(
        executionOrder {
            ymd(2023, 9, 13)
            author("ben")
        },
        forward = changes {
            createRole("FRED")
            grantPermissions("INSERT", "UPDATE", "DELETE") {
                on("KONTROL_DB_VERSIONING") {
                }
                objectType(DbObjectType.TABLE)
                to("FRED")
            }
            grantPermissions("SELECT") {
                on(DEFAULT_VERSION_CONTROL_TABLE)
                objectType("TABLE")
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
            revokePermissions("INSERT", "UPDATE", "DELETE", "SELECT") {
                on(DEFAULT_VERSION_CONTROL_TABLE)
                objectType("TABLE")
                to("FRED")
            }
            dropRole("FRED")
        },
        rollback = emptyList(),
    )

    @Test
    fun `can create a role and grant permissions to it`() {
        val result = dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    refactoring(::CreateARoleCalledFred)
                    refactoring(::DropTheRoleAgain)
                },
            )
        }
        result.use { it.applySql() }
    }
}

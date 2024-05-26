package net.futureset.kontroldb.samples

import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.modelchange.grantPermissions
import net.futureset.kontroldb.refactoring.Refactoring

class GrantPermissions : Refactoring(
    executionOrder {
        ymd(2023, 9, 29)
        author("ben")
    },
    forward = changes {
        grantPermissions("INSERT", "UPDATE", "DELETE") {
            on("CUSTOMER")
            objectType(DbObjectType.TABLE)
            to("A_NEW_ROLE")
        }
    },
    rollback = emptyList(),
)

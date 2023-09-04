package net.futureset.kmigrate.refactoring

import net.futureset.kmigrate.ExecutionOrder
import net.futureset.kmigrate.KMigrateDsl.Companion.changes
import net.futureset.kmigrate.Refactoring
import net.futureset.kmigrate.StandardColumnTypes.INT_16
import net.futureset.kmigrate.StandardColumnTypes.INT_32
import net.futureset.kmigrate.StandardColumnTypes.LOCALDATETIME
import net.futureset.kmigrate.StandardColumnTypes.Varchar
import net.futureset.kmigrate.settings.EffectiveSettings
import java.time.LocalDate

const val DEFAULT_VERSION_CONTROL_TABLE = "K_MIGRATE_CONTROL"

val VERSION_TABLE_ID = ExecutionOrder(LocalDate.of(2000, 1, 1), "system")

class CreateVersionControlTable(effectiveSettings: EffectiveSettings) : Refactoring(
    executionOrder = VERSION_TABLE_ID,
    forward = changes {
        createTable {
            table(effectiveSettings.versionControlTable)
            column("ID", Varchar(120))
            column("EXECUTION_ORDER", Varchar(20))
            column("EXECUTION_SEQUENCE", INT_32)
            column("LAST_APPLIED", LOCALDATETIME)
            column("FIRST_APPLIED", LOCALDATETIME)
            column("EXECUTION_FREQUENCY", Varchar(20))
            column("EXECUTION_COUNT", INT_16)
            column("CHECK_SUM", Varchar(23))
        }
    },
    rollbacks = changes {
        dropTable {
            table(effectiveSettings.versionControlTable)
        }
    },
)

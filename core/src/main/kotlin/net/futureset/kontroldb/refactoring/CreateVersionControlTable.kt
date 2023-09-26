package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.ExecutionOrder
import net.futureset.kontroldb.Refactoring
import net.futureset.kontroldb.StandardColumnTypes.INT_16
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.addPrimaryKey
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.settings.EffectiveSettings
import java.time.LocalDate

const val DEFAULT_VERSION_CONTROL_TABLE = "KONTROL_DB_VERSIONING"

val VERSION_TABLE_ID = ExecutionOrder(LocalDate.of(2000, 1, 1), "system")

const val ID_COLUMN = "ID"

const val EXECUTION_ORDER = "EXECUTION_ORDER"

const val EXECUTED_SEQUENCE = "EXECUTED_SEQUENCE"

const val LAST_APPLIED = "LAST_APPLIED"

const val FIRST_APPLIED = "FIRST_APPLIED"

const val EXECUTION_FREQUENCY = "EXECUTION_FREQUENCY"

const val EXECUTION_COUNT = "EXECUTION_COUNT"

const val CHECK_SUM = "CHECK_SUM"

class CreateVersionControlTable(effectiveSettings: EffectiveSettings) : Refactoring(
    executionOrder = VERSION_TABLE_ID,
    forward = changes {
        createTable {
            table(effectiveSettings.versionControlTable)
            column(ID_COLUMN, Varchar(120))
            column(EXECUTION_ORDER, Varchar(20))
            column(EXECUTED_SEQUENCE, INT_32)
            column(LAST_APPLIED, LOCALDATETIME)
            column(FIRST_APPLIED, LOCALDATETIME)
            column(EXECUTION_FREQUENCY, Varchar(20))
            column(EXECUTION_COUNT, INT_16)
            column(CHECK_SUM, Varchar(23))
        }
        addPrimaryKey {
            table(effectiveSettings.versionControlTable)
            constraintName(effectiveSettings.versionControlTable.name.name + "_PK")
            column(ID_COLUMN)
        }
    },
    rollback = changes {
        dropTable {
            table(effectiveSettings.versionControlTable)
        }
    },
)

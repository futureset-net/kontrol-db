package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.model.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.model.StandardColumnTypes.INT_16
import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
import net.futureset.kontroldb.model.StandardColumnTypes.INT_64
import net.futureset.kontroldb.model.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.addPrimaryKey
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.settings.EffectiveSettings

const val DEFAULT_VERSION_CONTROL_TABLE = "KONTROL_DB_VERSIONING"

const val ID_COLUMN = "ID"

const val EXECUTION_ORDER = "EXECUTION_ORDER"

const val EXECUTED_SEQUENCE = "EXECUTED_SEQUENCE"

const val LAST_APPLIED = "LAST_APPLIED"

const val FIRST_APPLIED = "FIRST_APPLIED"

const val EXECUTION_FREQUENCY = "EXECUTION_FREQUENCY"

const val EXECUTION_COUNT = "EXECUTION_COUNT"

const val MIGRATION_RUN_ID = "MIGRATION_RUN_ID"

const val CHECK_SUM = "CHECK_SUM"

const val ROLLED_BACK = "ROLLED_BACK"

class CreateVersionControlTable(effectiveSettings: EffectiveSettings) : Refactoring(
    executionOrder = EARLIEST_CHANGE,
    forward = changes {
        createTable(effectiveSettings.versionControlTable) {
            column(ID_COLUMN, Varchar(120))
            column(EXECUTION_ORDER, Varchar(24))
            column(EXECUTED_SEQUENCE, INT_32)
            column(LAST_APPLIED, LOCALDATETIME)
            column(FIRST_APPLIED, LOCALDATETIME)
            column(EXECUTION_FREQUENCY, Varchar(20))
            column(EXECUTION_COUNT, INT_16)
            column(MIGRATION_RUN_ID, INT_64)
            column(CHECK_SUM, Varchar(23))
            column(ROLLED_BACK, BOOLEAN)
        }
        addPrimaryKey(effectiveSettings.versionControlTable.schemaObject.name.name + "_PK") {
            table(effectiveSettings.versionControlTable)
            column(ID_COLUMN)
        }
    },
    rollback = changes {
        dropTable(effectiveSettings.versionControlTable)
    },
)

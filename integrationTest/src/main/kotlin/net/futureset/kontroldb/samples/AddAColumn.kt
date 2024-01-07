package net.futureset.kontroldb.samples

import net.futureset.kontroldb.model.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.addColumnsTo
import net.futureset.kontroldb.modelchange.dropColumnsFrom
import net.futureset.kontroldb.refactoring.Refactoring

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

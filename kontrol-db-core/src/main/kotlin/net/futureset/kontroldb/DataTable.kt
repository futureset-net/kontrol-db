package net.futureset.kontroldb

import java.lang.Integer.max

fun <T : Any> Iterable<T>.dataTable(vararg columns: Pair<String, (T) -> String?>): String {
    val columnWidths =
        columns.associate { (heading, cellFunction) ->
            Pair(heading, max(heading.length, this.maxOf { cellFunction(it)?.length ?: 0 }))
        }
    val horizontalLine =
        "=".repeat(columnWidths.values.sum() + columnWidths.size * 3 + 1)
    return (
        listOf(
            horizontalLine,
            columnWidths.entries
                .joinToString(separator = " | ", prefix = "| ", postfix = " |") { (header, width) ->
                    header.padEnd(width)
                },
            horizontalLine,
        ) +
            this.map { rowData ->
                columns.joinToString(separator = " | ", prefix = "| ", postfix = " |") { (header, cellFunction) ->
                    (cellFunction(rowData) ?: "").padEnd(columnWidths[header] ?: 0)
                }
            }
        ).joinToString(separator = "\n", prefix = "\n")
}

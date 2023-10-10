package net.futureset.kontroldb

data class KontrolDbState(
    val refactoringsInSourceControl: List<Refactoring>,
    val refactoringsToApply: List<Refactoring>,
    val lastExecutionSequence: Int,
    val appliedRefactorings: Map<String, AppliedRefactoring>,
) {
    override fun toString(): String {
        val columnWidths = mutableMapOf<String, Int>()
        columnWidths["SOURCE_CONTROL"] = refactoringsInSourceControl.maxOfOrNull { it.id().length } ?: 14
        columnWidths["WILL_APPLY"] = 10
        columnWidths["ALREADY_APPLIED"] = 15
        val horizontalLine = "=".repeat(columnWidths.keys.sumOf { columnWidth(it, columnWidths) } + columnWidths.size * 2 + 4)
        return (
            listOf(
                horizontalLine,
                listOf(
                    data("SOURCE_CONTROL", columnWidths),
                    data("WILL_APPLY", columnWidths),
                    data("ALREADY_APPLIED", columnWidths),
                ).joinToString(separator = " | ", prefix = "| ", postfix = " |"),
                horizontalLine,
            ) +
                refactoringsInSourceControl.map { refactoring ->
                    listOf(
                        data("SOURCE_CONTROL", columnWidths, refactoring.id()),
                        data("WILL_APPLY", columnWidths, if (refactoringsToApply.contains(refactoring)) "Y" else ""),
                        data(
                            "ALREADY_APPLIED",
                            columnWidths,
                            if (appliedRefactorings.containsKey(refactoring.id())) "Y" else "",
                        ),
                    ).joinToString(separator = " | ", prefix = "| ", postfix = " |")
                } +
                listOf(horizontalLine)
            ).joinToString(separator = "\n")
    }

    fun columnWidth(title: String, widths: Map<String, Int>): Int = widths[title] ?: title.length

    fun data(title: String, widths: Map<String, Int>, data: String = title): String {
        return data.padEnd(columnWidth(title, widths))
    }
}

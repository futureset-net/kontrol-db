package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

data class ColumnAndValue(
    val columnName: DbIdentifier,
    private val value: ColumnValue?,
    private val separator: String = "AS",
    private val columnFirst: Boolean = separator == "=",
) : SqlString {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(
            columnName.toQuoted(effectiveSettings),
            value?.toQuoted(effectiveSettings),
        ).let { it.takeIf { columnFirst } ?: it.reversed() }.joinToString(separator = " $separator ")
    }
}

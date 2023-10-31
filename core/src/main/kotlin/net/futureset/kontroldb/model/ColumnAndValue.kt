package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

data class ColumnAndValue(
    val columnName: DbIdentifier,
    val value: ColumnValue?,
    val separator: String = "AS",
    val columnFirst: Boolean = separator == "=",
) : SqlString {
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(
            columnName.toSql(effectiveSettings),
            value?.toSql(effectiveSettings),
        ).let { it.takeIf { columnFirst } ?: it.reversed() }.joinToString(separator = " $separator ")
    }
}

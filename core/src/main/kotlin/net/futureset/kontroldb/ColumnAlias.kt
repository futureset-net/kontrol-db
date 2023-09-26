package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.Operand
import net.futureset.kontroldb.settings.EffectiveSettings

data class ColumnAlias(
    val columnName: DbIdentifier,
    val alias: String?,
) : SqlString, Operand {
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return (alias?.let { "$it." } ?: "") + columnName.toSql(effectiveSettings)
    }
}

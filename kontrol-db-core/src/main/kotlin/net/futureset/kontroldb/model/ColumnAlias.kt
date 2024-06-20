package net.futureset.kontroldb.model

import net.futureset.kontroldb.modelchange.Operand
import net.futureset.kontroldb.settings.EffectiveSettings

data class ColumnAlias(
    val columnName: DbIdentifier,
    val alias: String?,
) : SqlString, Operand {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return (alias?.let { "$it." } ?: "") + columnName.toQuoted(effectiveSettings)
    }
}

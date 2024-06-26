package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.SqlString
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.settings.EffectiveSettings

data class TableAlias(
    val alias: String?,
    val table: Table,
) : ModelChange, SqlString {

    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return table.toQuoted(effectiveSettings) + alias?.let { " $it" }.orEmpty()
    }
}

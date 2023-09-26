package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SqlString
import net.futureset.kontroldb.settings.EffectiveSettings

data class TableAlias(
    val alias: String?,
    val table: SchemaObject,
) : ModelChange, SqlString {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return table.toSql(effectiveSettings) + alias?.let { " $it" }.orEmpty()
    }
}

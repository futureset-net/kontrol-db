package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SqlString
import net.futureset.kontroldb.settings.EffectiveSettings

data class Comment(val text: String) : ModelChange, SqlString {
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return if (effectiveSettings.isScripting) text else ""
    }
}

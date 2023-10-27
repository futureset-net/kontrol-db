package net.futureset.kontroldb

import com.fasterxml.jackson.annotation.JsonValue
import net.futureset.kontroldb.modelchange.Operand
import net.futureset.kontroldb.settings.EffectiveSettings

data class DbIdentifier(@JsonValue val name: String) : SqlString, Operand {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return effectiveSettings.openQuote + name + effectiveSettings.closeQuote
    }

    fun alias(label: String? = null) =
        ColumnAlias(this, label)
}

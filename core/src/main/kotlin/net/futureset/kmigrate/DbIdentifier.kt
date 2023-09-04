package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings

data class DbIdentifier(val name: String) : SqlString {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return effectiveSettings.openQuote + name + effectiveSettings.closeQuote
    }
}

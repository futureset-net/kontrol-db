package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings

data class Tablespace(val name: String) : SqlString {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return (
            effectiveSettings.openQuote + name +
                effectiveSettings.closeQuote
            ).takeIf { effectiveSettings.outputTablespace }.orEmpty()
    }
}

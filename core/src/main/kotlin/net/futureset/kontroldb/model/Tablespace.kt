package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

data class Tablespace(val name: String) : SqlString {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return (
            effectiveSettings.openQuote + name +
                effectiveSettings.closeQuote
            ).takeIf { effectiveSettings.isOutputTablespace }.orEmpty()
    }
}

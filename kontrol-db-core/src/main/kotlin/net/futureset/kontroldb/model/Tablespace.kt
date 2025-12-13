package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

data class Tablespace(
    val name: String,
) : SqlString {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String = (
        effectiveSettings.openQuote + name +
            effectiveSettings.closeQuote
        ).takeIf { effectiveSettings.isOutputTablespace }.orEmpty()
}

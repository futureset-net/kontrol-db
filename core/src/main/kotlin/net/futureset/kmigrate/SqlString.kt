package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings

interface SqlString {

    fun toSql(effectiveSettings: EffectiveSettings): String
}

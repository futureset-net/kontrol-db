package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

interface SqlString {

    fun toSql(effectiveSettings: EffectiveSettings): String
}

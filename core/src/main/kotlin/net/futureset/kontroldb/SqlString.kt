package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

interface SqlString {

    fun toSql(effectiveSettings: EffectiveSettings): String
}

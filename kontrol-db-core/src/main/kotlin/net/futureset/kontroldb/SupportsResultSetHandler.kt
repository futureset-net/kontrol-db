package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings
import java.sql.ResultSet

interface SupportsResultSetHandler {

    fun resultSetHandler(effectiveSettings: EffectiveSettings): ((ResultSet) -> Unit)?
}

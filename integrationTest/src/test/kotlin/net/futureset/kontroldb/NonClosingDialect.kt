package net.futureset.kontroldb

import net.futureset.kontroldb.settings.DbDialect
import java.sql.Connection

class NonClosingDialect(private val delegate: DbDialect) : DbDialect by delegate {

    override fun closeHook(): (Connection) -> Unit = {}
}

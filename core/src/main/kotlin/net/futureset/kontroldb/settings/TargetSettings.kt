package net.futureset.kontroldb.settings

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE

interface ITargetSettings {
    val jdbcUrl: String?
    val username: String?
    val password: String?
    val defaultTablespace: DbIdentifier?
    val defaultIndexTablespace: DbIdentifier?
    val defaultSchema: DbIdentifier?
    val defaultCatalog: DbIdentifier?
    val versionControlTable: SchemaObject
}

data class TargetSettings(

    override val jdbcUrl: String?,
    override val username: String? = null,
    override val password: String? = null,
    override val defaultTablespace: DbIdentifier? = null,
    override val defaultIndexTablespace: DbIdentifier? = null,
    override val defaultSchema: DbIdentifier? = null,
    override val defaultCatalog: DbIdentifier? = null,
    override val versionControlTable: SchemaObject,

) : ITargetSettings {
    fun builder() = TargetSettingsBuilder(this)
}

class TargetSettingsBuilder(

    private var targetSettings: TargetSettings = TargetSettings(
        jdbcUrl = "jdbc:hsqldb:mem:test",
        versionControlTable = SchemaObject(name = DbIdentifier(DEFAULT_VERSION_CONTROL_TABLE)),
    ),

) : Builder<TargetSettingsBuilder, TargetSettings> {
    fun versionControlTable(block: SchemaObjectBuilder.() -> Unit) = apply {
        targetSettings = targetSettings.copy(
            versionControlTable = SchemaObjectBuilder(targetSettings.versionControlTable).apply(block).build(),
        )
    }

    fun jdbcUrl(jdbcUrl: String) = apply {
        targetSettings = targetSettings.copy(jdbcUrl = jdbcUrl)
    }

    fun username(username: String) = apply {
        targetSettings = targetSettings.copy(username = username)
    }

    fun password(password: String) = apply {
        targetSettings = targetSettings.copy(password = password)
    }

    fun defaultTablespace(defaultTablespace: String) = apply {
        targetSettings = targetSettings.copy(defaultTablespace = DbIdentifier(defaultTablespace))
    }

    fun defaultIndexTablespace(defaultIndexTablespace: String) = apply {
        targetSettings = targetSettings.copy(defaultIndexTablespace = DbIdentifier(defaultIndexTablespace))
    }

    fun defaultSchema(defaultSchema: String) = apply {
        targetSettings = targetSettings.copy(defaultSchema = DbIdentifier(defaultSchema))
    }

    fun defaultCatalog(defaultCatalog: String) = apply {
        targetSettings = targetSettings.copy(defaultCatalog = DbIdentifier(defaultCatalog))
    }

    override fun build(): TargetSettings {
        return targetSettings
    }
}

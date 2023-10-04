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

) : ITargetSettings

data class TargetSettingsBuilder(

    private var jdbcUrl: String? = null,
    private var username: String? = null,
    private var password: String? = null,
    private var defaultTablespace: DbIdentifier? = null,
    private var defaultIndexTablespace: DbIdentifier? = null,
    private var defaultSchema: DbIdentifier? = null,
    private var defaultCatalog: DbIdentifier? = null,
    private var versionControlTable: SchemaObject = SchemaObjectBuilder().name(DEFAULT_VERSION_CONTROL_TABLE).build(),
) : Builder<TargetSettingsBuilder, TargetSettings> {
    fun versionControlTable(block: SchemaObjectBuilder.() -> Unit) = apply {
        versionControlTable = SchemaObjectBuilder().apply(block).build()
    }

    fun jdbcUrl(jdbcUrl: String) = apply {
        this.jdbcUrl = jdbcUrl
    }

    fun username(username: String) = apply {
        this.username = username
    }

    fun password(password: String) = apply {
        this.password = password
    }

    fun defaultTablespace(defaultTablespace: String) = apply {
        this.defaultTablespace = DbIdentifier(defaultTablespace)
    }

    fun defaultIndexTablespace(defaultIndexTablespace: String) = apply {
        this.defaultIndexTablespace = DbIdentifier(defaultIndexTablespace)
    }

    fun defaultSchema(defaultSchema: String) = apply {
        this.defaultSchema = DbIdentifier(defaultSchema)
    }

    fun defaultCatalog(defaultCatalog: String) = apply {
        this.defaultCatalog = DbIdentifier(defaultCatalog)
    }

    override fun build(): TargetSettings {
        return TargetSettings(
            jdbcUrl = jdbcUrl,
            username = username,
            password = password,
            defaultTablespace = defaultTablespace,
            defaultIndexTablespace = defaultIndexTablespace,
            defaultCatalog = defaultCatalog,
            defaultSchema = defaultSchema,
            versionControlTable = versionControlTable,
        )
    }
}

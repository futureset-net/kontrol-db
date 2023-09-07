package net.futureset.kontroldb.settings

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.SchemaObjectBuilder
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE

data class TargetSettings(

    val jdbcUrl: String?,
    val username: String? = null,
    val password: String? = null,
    val defaultTablespace: String? = null,
    val defaultIndexTablespace: String? = null,
    val defaultSchema: String? = null,
    val defaultCatalog: String? = null,
    val versionControlTable: SchemaObject,

)

data class TargetSettingsBuilder(

    private var jdbcUrl: String? = null,
    private var username: String? = null,
    private var password: String? = null,
    private var defaultTablespace: String? = null,
    private var defaultIndexTablespace: String? = null,
    private var defaultSchema: String? = null,
    private var defaultCatalog: String? = null,
    private var versionControlTable: SchemaObject = SchemaObjectBuilder().name(DEFAULT_VERSION_CONTROL_TABLE).build(),
) : Builder<TargetSettings> {
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
        this.defaultTablespace = defaultTablespace
    }

    fun defaultIndexTablespace(defaultIndexTablespace: String) = apply {
        this.defaultIndexTablespace = defaultIndexTablespace
    }

    fun defaultSchema(defaultSchema: String) = apply {
        this.defaultSchema = defaultSchema
    }

    fun defaultCatalog(defaultCatalog: String) = apply {
        this.defaultCatalog = defaultCatalog
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

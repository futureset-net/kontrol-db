package net.futureset.kontroldb.settings

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE

interface ITargetSettings {
    val jdbcUrl: String?
    val username: String?
    val password: String?
    val defaultTablespace: DbIdentifier?
    val defaultIndexTablespace: DbIdentifier?
    val defaultSchema: DbIdentifier?
    val defaultCatalog: DbIdentifier?
    val versionControlTable: Table
}

data class TargetSettings(

    override val jdbcUrl: String?,
    override val username: String? = "sa",
    override val password: String? = null,
    override val defaultTablespace: DbIdentifier? = null,
    override val defaultIndexTablespace: DbIdentifier? = null,
    override val defaultSchema: DbIdentifier? = null,
    override val defaultCatalog: DbIdentifier? = null,
    override val versionControlTable: Table,

) : ITargetSettings {
    fun builder() = TargetSettingsBuilder(this)
}

class TargetSettingsBuilder(

    private var targetSettings: TargetSettings = TargetSettings(
        jdbcUrl = "jdbc:hsqldb:mem:test",
        username = "sa",
        versionControlTable = Table(schemaObject = SchemaObject(name = DbIdentifier(DEFAULT_VERSION_CONTROL_TABLE)), tablePersistence = TablePersistence.NORMAL),
    ),

) : Builder<TargetSettingsBuilder, TargetSettings> {
    fun versionControlTable(block: SchemaObjectBuilder.() -> Unit) = apply {
        targetSettings = targetSettings.copy(
            versionControlTable = Table(SchemaObjectBuilder(targetSettings.versionControlTable.schemaObject).apply(block).build(), TablePersistence.NORMAL),
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

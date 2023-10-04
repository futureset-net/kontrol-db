package net.futureset.kontroldb

import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.settings.DbDialect
import net.futureset.kontroldb.settings.ExecutionSettingsBuilder
import net.futureset.kontroldb.settings.TargetSettingsBuilder
import org.koin.core.module.Module

class KontrolDb {

    private var kontrolDbEngineBuilder = KontrolDbEngine.KontrolDbEngineBuilder()
    private var targetSettingsBuilder: TargetSettingsBuilder = TargetSettingsBuilder(
        jdbcUrl = "jdbc:hsqldb:mem:testdb",
        versionControlTable = SchemaObject(name = DbIdentifier(name = DEFAULT_VERSION_CONTROL_TABLE)),
    )

    fun dbSettings(block: TargetSettingsBuilder.() -> Unit) {
        targetSettingsBuilder.apply(block)
    }

    fun executionSettings(block: ExecutionSettingsBuilder.() -> Unit) {
        kontrolDbEngineBuilder.executionSettings = ExecutionSettingsBuilder().apply(block).build()
    }

    fun dbDialect(dbDialect: DbDialect) {
        kontrolDbEngineBuilder.dbDialect = dbDialect
    }

    fun model(): KontrolDbEngine {
        kontrolDbEngineBuilder.targetSettings = targetSettingsBuilder.build()
        return kontrolDbEngineBuilder.build()
    }

    fun changeModules(module: Module) {
        kontrolDbEngineBuilder.modules.add(module)
    }

    companion object {

        fun dsl(lambda: KontrolDb.() -> Unit): KontrolDbEngine {
            return KontrolDb().apply(lambda).model()
        }
    }
}

package net.futureset.kmigrate

import net.futureset.kmigrate.refactoring.CreateVersionControlTable
import net.futureset.kmigrate.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kmigrate.settings.DbDialect
import net.futureset.kmigrate.settings.EffectiveSettings
import net.futureset.kmigrate.settings.ExecutionSettings
import net.futureset.kmigrate.settings.TargetSettings
import net.futureset.kmigrate.settings.TargetSettingsBuilder
import net.futureset.kmigrate.targetsystem.HsqlDbDialect
import net.futureset.kmigrate.template.coreTemplateModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.onClose
import org.koin.logger.SLF4JLogger

class KMigrateDsl {

    private var ansiDialect: AnsiDialect? = null
    private var modules = mutableListOf<Module>()
    private var targetSettings: TargetSettings = TargetSettings(
        jdbcUrl = "jdbc:hsqldb:mem:testdb",
        versionControlTable = SchemaObject(name = DbIdentifier(name = DEFAULT_VERSION_CONTROL_TABLE)),
    )

    fun dbSettings(block: TargetSettingsBuilder.() -> Unit) {
        targetSettings = TargetSettingsBuilder().jdbcUrl(targetSettings.jdbcUrl ?: "").apply(block).build()
    }

    fun model(): KMigrate {
        val coreModule = module {
            singleOf(::CreateVersionControlTable).bind(Refactoring::class)
            single<AnsiDialect> { ansiDialect ?: HsqlDbDialect() }.bind(DbDialect::class)
            single {
                EffectiveSettings(get<DbDialect>(), get<ExecutionSettings>(), get<TargetSettings>())
            }
            single<TargetSettings> { targetSettings }
            single { ExecutionSettings() }
            includes(coreTemplateModule)
            singleOf(::SqlExecutor).onClose { it?.close() }
//            singleOf(::KMigrate)
            single { KMigrate(getAll(), get(), getAll(), get()) }
        }
        try {
            val fred = startKoin {
                logger(SLF4JLogger(level = Level.INFO))
                modules(coreModule)
                modules(modules)
            }
            return fred.koin.get<KMigrate>()
        } finally {
            stopKoin()
        }
    }

    fun changeModules(module: Module) {
        modules.add(module)
    }

    companion object {

        fun changes(vararg changes: ModelChange, lambda: ModelChangesBuilder.() -> Unit): List<ModelChange> {
            return changes.asList() + ModelChangesBuilder().apply(lambda).build()
        }

        fun executionOrder(lambda: ExecutionOrderBuilder.() -> Unit): ExecutionOrder {
            return ExecutionOrderBuilder().executionOrder(lambda)
        }

        fun kmigrate(lambda: KMigrateDsl.() -> Unit): KMigrate {
            return KMigrateDsl().apply(lambda).model()
        }
    }
}

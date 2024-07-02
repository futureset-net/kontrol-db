package net.futureset.kontroldb

import net.futureset.kontroldb.config.ConfigFileControl
import net.futureset.kontroldb.config.KontrolDbConfig
import net.futureset.kontroldb.core.CoreModule
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorFactory
import net.futureset.kontroldb.migration.ApplyDirectlyMigrationHandler
import net.futureset.kontroldb.migration.WriteChangesToFileMigrationHandler
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.settings.DbDialect
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.settings.ExecutionSettings
import net.futureset.kontroldb.settings.ExecutionSettingsBuilder
import net.futureset.kontroldb.settings.IExecutionSettings
import net.futureset.kontroldb.settings.ITargetSettings
import net.futureset.kontroldb.settings.TargetSettings
import net.futureset.kontroldb.settings.TargetSettingsBuilder
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ksp.generated.module
import org.koin.logger.SLF4JLogger
import java.nio.file.Paths
import kotlin.io.path.absolute

data class KontrolDbEngineBuilder(
    private var dialect: String = "default",
    private var targetSettings: TargetSettings = TargetSettings(
        jdbcUrl = "jdbc:hsqldb:mem:testdb",
        versionControlTable = Table(SchemaObject(name = DbIdentifier(name = DEFAULT_VERSION_CONTROL_TABLE))),
    ),
    private var executionSettings: ExecutionSettings = ExecutionSettings(
        isOutputCatalog = true,
        isOutputSchema = true,
        isOutputTablespace = true,
        externalFileRoot = Paths.get("").absolute(),
    ),
    private var modules: MutableSet<Module> = mutableSetOf(),
) : Builder<KontrolDbEngineBuilder, KontrolDbEngine> {

    private val configFileControl: ConfigFileControl = ConfigFileControl()

    override fun build(): KontrolDbEngine {
        val engineModule = module {
            singleOf(::SqlGeneratorFactory)
            singleOf(::CreateVersionControlTable).bind(Refactoring::class)
            single {
                EffectiveSettings(
                    requireNotNull(
                        getAll<DbDialect>().sorted().reversed().firstOrNull { it.databaseName == dialect }
                            .also { logger.info("selected dialect $it") },
                    ) { "Could not find dialect named '$dialect'" },
                    get<IExecutionSettings>(),
                    get<ITargetSettings>(),
                    get<SqlGeneratorFactory>(),
                )
            }
            single<TargetSettings> { targetSettings }.bind(ITargetSettings::class)
            single { executionSettings }.bind(IExecutionSettings::class)
            includes(CoreModule().module)
            singleOf(::ApplyDirectlyMigrationHandler)
            singleOf(::WriteChangesToFileMigrationHandler)
            single { KontrolDbEngine(getAll(), get(), get(), get(), get()) }
        }

        val buildEngine = startKoin {
//            allowOverride(false)
            logger(SLF4JLogger(level = Level.INFO))
            modules(engineModule)
            modules(modules.toList())
        }
        buildEngine.koin.get<SqlGeneratorFactory>().run {
            buildEngine.koin.getAll<SqlGenerator<ModelChange>>().forEach(::register)
        }
        return buildEngine.koin.get<KontrolDbEngine>()
    }

    fun loadConfig(path: String) = apply {
        val config = configFileControl.configFile(
            Paths.get(path),
            KontrolDbConfig(
                dialect = dialect,
                modules = modules.mapNotNull { it::class.qualifiedName },
                targetSettings = targetSettings,
                executionSettings = executionSettings,
            ),
        )
        this.dialect = config.dialect ?: this.dialect
        this.targetSettings = config.targetSettings ?: this.targetSettings
        this.executionSettings = config.executionSettings ?: this.executionSettings
        modules.addAll(config.resolvedModules())
    }

    fun dialect(dialect: String) = apply {
        this.dialect = dialect
    }

    fun dbSettings(block: TargetSettingsBuilder.() -> Unit) = apply {
        targetSettings = TargetSettingsBuilder(targetSettings).apply(block).build()
    }

    fun executionSettings(block: ExecutionSettingsBuilder.() -> Unit) = apply {
        executionSettings = ExecutionSettingsBuilder(executionSettings).apply(block).build()
    }

    fun changeModules(module: Module) = apply {
        modules.add(module)
    }

    companion object {
        fun dsl(lambda: KontrolDbEngineBuilder.() -> Unit): KontrolDbEngine {
            return KontrolDbEngineBuilder().apply(lambda).build()
        }
    }
}

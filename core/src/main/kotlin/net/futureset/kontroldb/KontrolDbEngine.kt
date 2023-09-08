package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.modelchange.InsertRowBuilder.Companion.insertRow
import net.futureset.kontroldb.modelchange.UpdateBuilder.Companion.updateRow
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.settings.DbDialect
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.settings.ExecutionSettings
import net.futureset.kontroldb.settings.TargetSettings
import net.futureset.kontroldb.targetsystem.HsqlDbDialect
import net.futureset.kontroldb.template.coreTemplateModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.onClose
import org.koin.logger.SLF4JLogger
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.SortedSet
import kotlin.io.path.writeText
import kotlin.reflect.KClass

@DslMarker
annotation class KontrolDbDslMarker

data class KontrolDbEngine(
    private val allRefactoring: List<Refactoring>,
    val effectiveSettings: EffectiveSettings,
    val templates: List<SqlTemplate<ModelChange>>,
    val sqlExecutor: SqlExecutor,
) {

    private val templatesByType = HashMap<KClass<ModelChange>, SortedSet<SqlTemplate<ModelChange>>>()

    val refactorings = allRefactoring.toSortedSet()
    init {
        require(templates.isNotEmpty()) { "Could not find any templates" }
        templates.forEach {
            templatesByType.computeIfAbsent(it.type()) { sortedSetOf() }.add(it)
        }
    }

    private val logger = LoggerFactory.getLogger(KontrolDbEngine::class.java)

    fun generateSql(output: Path) {
        effectiveSettings.isScripting = true
        output.parent?.let(Files::createDirectories)

        val generateSql = generateSql()
        output.writeText(generateSql.joinToString(separator = "\n\n", postfix = "\n"))
    }

    fun getCurrentState(): SortedSet<AppliedRefactoring> {
        return sqlExecutor.getCurrentState()
    }

    fun applySql(): Int {
        effectiveSettings.isScripting = false
        val sqlToApply = generateSql().filter { it.isNotBlank() }
        sqlExecutor.executeAll(sqlToApply)
        return sqlToApply.size
    }

    private fun generateSql(): List<String> {
        val currentState = sqlExecutor.getCurrentState().associateBy(AppliedRefactoring::id)
        val lastSequence = currentState.values.maxOfOrNull { it.executionSequence } ?: 1
        val changes = refactorings.toSortedSet()
            .filter { shouldRun(currentState, it) }
            .toList()
        return changes
            .flatMapIndexed { i, refactoring ->
                listOf(Comment("Start ${refactoring.id()}\nContains ${refactoring.forward.size} changes")) + refactoring.forward + logInVersionControl(i + lastSequence, refactoring, currentState[refactoring.id()])
            }
            .toMutableList()
            .apply {
                add(
                    0,
                    Comment(
                        """
kontrol-db

Contains ${changes.size} refactorings
${changes.map{it::class.qualifiedName + " " + it.id()}.joinToString(separator = "\n")}


Generated on ${ZonedDateTime.now().withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}    
                        """.trimIndent(),
                    ),
                )
            }
            .mapNotNull { modelChange ->
                requireNotNull(templatesByType[modelChange::class]) {
                    "No template found for ${modelChange.getName()}"
                }.firstOrNull(SqlTemplate<ModelChange>::canApply)?.convert(modelChange)
            }
    }

    private fun shouldRun(
        currentState: Map<String, AppliedRefactoring>,
        refactoring: Refactoring,
    ): Boolean {
        val previousExecution = currentState[refactoring.id()] ?: run { logger.info("Run ${refactoring.id()}"); return true }
        return when (refactoring.executeMode) {
            ExecuteMode.ALWAYS -> { logger.info("Always run ${refactoring.id()}"); true }
            ExecuteMode.ONCE -> {
                check(previousExecution.checksum == refactoring.checkSum()) {
                    "Checksum mismatch for ${refactoring.id()} checksum ${refactoring.checkSum()}!=${previousExecution.checksum}"
                }; false
            }
            ExecuteMode.ON_CHANGE -> (previousExecution.checksum != refactoring.checkSum()).apply {
                if (this) {
                    logger.info("Checksum changed from/to '${previousExecution.checksum}/${refactoring.checkSum()}' so re-running ${refactoring.id()}")
                }
            }
        }
    }

    private fun logInVersionControl(
        index: Int,
        refactoring: Refactoring,
        appliedRefactoring: AppliedRefactoring?,
    ): ModelChange {
        if (appliedRefactoring != null) {
            return updateRow {
                table(effectiveSettings.versionControlTable)
                setValueFunction("LAST_APPLIED", effectiveSettings.now())
                setValueFunction("EXECUTION_COUNT", "EXECUTION_COUNT+1")
                setValue("EXECUTED_SEQUENCE", index)
                whereValue("ID", appliedRefactoring.id)
            }
        }
        return insertRow {
            table(effectiveSettings.versionControlTable)
            value("ID", refactoring.id())
            value("EXECUTION_FREQUENCY", refactoring.executeMode.name)
            valueExpression("FIRST_APPLIED", effectiveSettings.now())
            valueExpression("LAST_APPLIED", effectiveSettings.now())
            value("EXECUTION_ORDER", refactoring.executionOrder.toSingleValue())
            value("EXECUTED_SEQUENCE", index)
            value("EXECUTION_COUNT", 1)
            value("CHECK_SUM", refactoring.checkSum())
        }
    }
}

data class KontrolDbEngineBuilder(
    var dbDialect: DbDialect = HsqlDbDialect(),
    var targetSettings: TargetSettings = TargetSettings(
        jdbcUrl = "jdbc:hsqldb:mem:testdb",
        versionControlTable = SchemaObject(name = DbIdentifier(name = DEFAULT_VERSION_CONTROL_TABLE)),
    ),
    var modules: MutableList<Module> = mutableListOf(),
) : Builder<KontrolDbEngine> {
    override fun build(): KontrolDbEngine {
        val coreModule = module {
            singleOf(::CreateVersionControlTable).bind(Refactoring::class)
            single<DbDialect> { dbDialect }.bind(DbDialect::class)
            single {
                EffectiveSettings(get<DbDialect>(), get<ExecutionSettings>(), get<TargetSettings>())
            }
            single<TargetSettings> { targetSettings }
            single { ExecutionSettings(outputCatalog = true, outputSchema = true) }
            includes(coreTemplateModule)
            singleOf(::SqlExecutor).onClose { it?.close() }
            single { KontrolDbEngine(getAll(), get(), getAll(), get()) }
        }
        try {
            val buildEngine = startKoin {
                logger(SLF4JLogger(level = Level.INFO))
                modules(coreModule)
                modules(modules)
            }
            return buildEngine.koin.get<KontrolDbEngine>()
        } finally {
            stopKoin()
        }
    }
}

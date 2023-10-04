package net.futureset.kontroldb

import net.futureset.kontroldb.ColumnValue.Companion.value
import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.modelchange.Insert.InsertBuilder.Companion.insertRow
import net.futureset.kontroldb.modelchange.Update.UpdateBuilder.Companion.updateRow
import net.futureset.kontroldb.refactoring.CHECK_SUM
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.refactoring.EXECUTED_SEQUENCE
import net.futureset.kontroldb.refactoring.EXECUTION_COUNT
import net.futureset.kontroldb.refactoring.EXECUTION_FREQUENCY
import net.futureset.kontroldb.refactoring.EXECUTION_ORDER
import net.futureset.kontroldb.refactoring.FIRST_APPLIED
import net.futureset.kontroldb.refactoring.ID_COLUMN
import net.futureset.kontroldb.refactoring.LAST_APPLIED
import net.futureset.kontroldb.refactoring.MIGRATION_RUN_ID
import net.futureset.kontroldb.refactoring.StartMigration
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
import java.nio.file.Path
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.SortedSet
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.notExists
import kotlin.io.path.writeText

data class KontrolDbEngine(
    private val allRefactoring: List<Refactoring>,
    val effectiveSettings: EffectiveSettings,
    val templateResolver: TemplateResolver,
    val sqlExecutor: SqlExecutor,
) : KontrolDbCommands, AutoCloseable {

    val refactorings = allRefactoring.toSortedSet()

    init {
        effectiveSettings.templateResolver = templateResolver
        require(refactorings.size == allRefactoring.size) {
            "Each refactoring must be unique"
        }
    }

    private val logger = LoggerFactory.getLogger(KontrolDbEngine::class.java)

    override fun generateSql(outputDirectory: Path) {
        effectiveSettings.isScripting = true
        require(outputDirectory.notExists() || outputDirectory.isDirectory())
        outputDirectory.createDirectories()

        val generateSql = generateSql()
        outputDirectory.resolve("output.sql").writeText(
            generateSql.joinToString(
                separator = effectiveSettings.operatingSystem.lineSeparator.repeat(2),
                postfix = effectiveSettings.operatingSystem.lineSeparator,
            ),
        )
    }

    override fun getCurrentState(): SortedSet<AppliedRefactoring> {
        return calculateState().appliedChanges.values.toSortedSet()
    }

    override fun applySql(): Int {
        effectiveSettings.isScripting = false
        val sqlToApply = generateSql().filter { it.isNotBlank() }
        sqlExecutor.executeAll(sqlToApply)
        return sqlToApply.size
    }

    override fun getNextRefactorings(): List<Refactoring> {
        return calculateState().changesToApply
    }

    private fun calculateState(): KontrolDbState {
        val currentState = sqlExecutor.getCurrentState().associateBy(AppliedRefactoring::id)
        refactorings.removeIf { it is StartMigration }
        if (refactorings.any { shouldRun(currentState, it) }) {
            refactorings.add(StartMigration())
        }
        return KontrolDbState(
            allSourceControlledChanges = refactorings.toSortedSet().toList(),
            appliedChanges = currentState,
            changesToApply = refactorings.toSortedSet()
                .filter { shouldRun(currentState, it) }
                .toList(),
            lastExecutionSequence = currentState.values.maxOfOrNull { it.executionSequence } ?: 1,
        )
            .apply {
                logger.debug("State\n{}\n", this)
            }
    }

    private fun generateSql(): List<String> {
        val state = calculateState()
        return state.changesToApply
            .flatMapIndexed { i, refactoring ->
                listOf(Comment("Start ${refactoring.id()}\nContains ${refactoring.forward.size} changes")) + refactoring.forward + logInVersionControl(
                    i + state.lastExecutionSequence,
                    refactoring,
                    state.appliedChanges[refactoring.id()],
                )
            }
            .toMutableList()
            .apply {
                add(
                    0,
                    Comment(
                        """
kontrol-db

Contains ${state.changesToApply.size} refactorings
${state.changesToApply.joinToString(separator = "\n") { it::class.qualifiedName + " " + it.id() }}


Generated on ${ZonedDateTime.now().withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}    
                        """.trimIndent(),
                    ),
                )
            }
            .flatMap { templateResolver.findTemplate(it)?.convert(it).orEmpty() }
            .mapNotNull { "$it${effectiveSettings.statementSeparator}" }
    }

    private fun shouldRun(
        currentState: Map<String, AppliedRefactoring>,
        refactoring: Refactoring,
    ): Boolean {
        val previousExecution =
            currentState[refactoring.id()] ?: run { logger.info("Run ${refactoring.id()}"); return true }
        return when (refactoring.executeMode) {
            ExecuteMode.ALWAYS -> {
                logger.info("Always run ${refactoring.id()}"); true
            }

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
                set(LAST_APPLIED to ColumnValue.expression(effectiveSettings.now()))
                set(EXECUTION_COUNT to ColumnValue.expression("$EXECUTION_COUNT+1"))
                set(EXECUTED_SEQUENCE to value(index))
                set(MIGRATION_RUN_ID to value(effectiveSettings.migrationRunId))
                where {
                    DbIdentifier(ID_COLUMN) eq value(appliedRefactoring.id)
                }
            }
        }
        return insertRow {
            table(effectiveSettings.versionControlTable)
            values {
                value(ID_COLUMN, refactoring.id())
                value(EXECUTION_FREQUENCY, refactoring.executeMode.name)
                valueExpression(FIRST_APPLIED, effectiveSettings.now())
                valueExpression(LAST_APPLIED, effectiveSettings.now())
                value(EXECUTION_ORDER, refactoring.executionOrder.toSingleValue())
                value(MIGRATION_RUN_ID, effectiveSettings.migrationRunId)
                value(EXECUTED_SEQUENCE, index)
                value(EXECUTION_COUNT, 1)
                value(CHECK_SUM, refactoring.checkSum())
            }
        }
    }

    override fun close() {
        stopKoin()
    }

    data class KontrolDbEngineBuilder(
        var dbDialect: DbDialect = HsqlDbDialect(),
        var targetSettings: TargetSettings = TargetSettings(
            jdbcUrl = "jdbc:hsqldb:mem:testdb",
            versionControlTable = SchemaObject(name = DbIdentifier(name = DEFAULT_VERSION_CONTROL_TABLE)),
        ),
        var executionSettings: ExecutionSettings = ExecutionSettings(
            isOutputCatalog = true,
            isOutputSchema = true,
            isOutputTablespace = true,
        ),
        var modules: MutableList<Module> = mutableListOf(),
    ) : Builder<KontrolDbEngineBuilder, KontrolDbEngine> {

        override fun build(): KontrolDbEngine {
            val coreModule = module {
                singleOf(::CreateVersionControlTable).bind(Refactoring::class)
                single<DbDialect> { dbDialect }.bind(DbDialect::class)
                single {
                    EffectiveSettings(
                        get<DbDialect>(),
                        get<ExecutionSettings>(),
                        get<TargetSettings>(),
                        System.currentTimeMillis(),
                    )
                }
                single {
                    val templates = getAll<SqlTemplate<ModelChange>>()
                    val result = TemplateResolver(templates)
                    templates.forEach { it.templateResolver = result }
                    result
                }
                single<TargetSettings> { targetSettings }
                single { executionSettings }
                includes(coreTemplateModule)
                singleOf(::SqlExecutor).onClose { it?.close() }
                single { KontrolDbEngine(getAll(), get(), get(), get()) }
            }

            val buildEngine = startKoin {
                logger(SLF4JLogger(level = Level.INFO))
                modules(coreModule)
                modules(modules)
            }
            return buildEngine.koin.get<KontrolDbEngine>()
        }
    }
}

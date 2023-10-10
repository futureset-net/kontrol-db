package net.futureset.kontroldb

import net.futureset.kontroldb.ColumnValue.Companion.column
import net.futureset.kontroldb.ColumnValue.Companion.value
import net.futureset.kontroldb.ExecuteMode.ALWAYS
import net.futureset.kontroldb.ExecuteMode.ONCE
import net.futureset.kontroldb.ExecuteMode.ON_CHANGE
import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.modelchange.ExecutesSql
import net.futureset.kontroldb.modelchange.Insert.InsertBuilder.Companion.insertRow
import net.futureset.kontroldb.modelchange.Update.UpdateBuilder.Companion.updateRow
import net.futureset.kontroldb.modelchange.executeQuery
import net.futureset.kontroldb.modelchange.sqlLogger
import net.futureset.kontroldb.refactoring.AStartEndMarker
import net.futureset.kontroldb.refactoring.CHECK_SUM
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.DEFAULT_VERSION_CONTROL_TABLE
import net.futureset.kontroldb.refactoring.EXECUTED_SEQUENCE
import net.futureset.kontroldb.refactoring.EXECUTION_COUNT
import net.futureset.kontroldb.refactoring.EXECUTION_FREQUENCY
import net.futureset.kontroldb.refactoring.EXECUTION_ORDER
import net.futureset.kontroldb.refactoring.EndMigration
import net.futureset.kontroldb.refactoring.FIRST_APPLIED
import net.futureset.kontroldb.refactoring.ID_COLUMN
import net.futureset.kontroldb.refactoring.LAST_APPLIED
import net.futureset.kontroldb.refactoring.MIGRATION_RUN_ID
import net.futureset.kontroldb.refactoring.ROLLED_BACK
import net.futureset.kontroldb.refactoring.StartMigration
import net.futureset.kontroldb.settings.DbDialect
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.settings.ExecutionSettings
import net.futureset.kontroldb.settings.TargetSettings
import net.futureset.kontroldb.settings.TransactionScope.MIGRATION
import net.futureset.kontroldb.settings.TransactionScope.REFACTORING
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
import java.sql.SQLException
import java.util.*
import kotlin.reflect.KClass

data class KontrolDbEngine(
    private val allRefactoring: List<Refactoring>,
    override val effectiveSettings: EffectiveSettings,
    val templateResolver: TemplateResolver,
    val sqlExecutor: ApplyDirectlyMigrationHandler,
    val applyToScript: WriteChangesToFileMigrationHandler,
) : KontrolDbCommands, AutoCloseable, ExecutesSql {

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
        applyToScript.outputDirectory = outputDirectory
        generateChanges(applyToScript, false)
    }

    override fun generateSqlRollback(outputDirectory: Path) {
        effectiveSettings.isScripting = true
        applyToScript.outputDirectory = outputDirectory
        generateChanges(applyToScript, true)
    }

    override fun applySql(): Int {
        effectiveSettings.isScripting = false
        return generateChanges(sqlExecutor, false).size
    }

    override fun getNextRefactorings(): List<Refactoring> {
        return calculateState().refactoringsToApply
    }

    private fun calculateState(): KontrolDbState {
        val currentState = getAppliedRefactorings().associateBy(AppliedRefactoring::id)
        val orderedChanges = refactorings.filterNot { it is StartMigration }.toSortedSet().toList()
        return KontrolDbState(
            refactoringsInSourceControl = orderedChanges,
            appliedRefactorings = currentState,
            refactoringsToApply = orderedChanges.filter { shouldRun(currentState, it) }.toList(),
            lastExecutionSequence = currentState.values.maxOfOrNull { it.executionSequence } ?: 1,
        )
    }

    private fun <T : Refactoring, U : Refactoring> MutableList<Refactoring>.addFirstButAfterRefactoringOfType(
        theType: KClass<T>,
        aRefactoring: U,
    ) {
        val index = indexOfFirst { theType.isInstance(it) }
        add(if (index < 0) 0 else index + 1, aRefactoring)
    }
    private fun <T : Refactoring, U : Refactoring> MutableList<Refactoring>.addLastButBeforeRefactoringOfType(
        theType: KClass<T>,
        aRefactoring: U,
    ) {
        val index = indexOfLast { theType.isInstance(it) }
        add(if (index < 0) this.size else index, aRefactoring)
    }
    private fun generateChanges(migrationHandler: MigrationHandler, rollback: Boolean): List<String> {
        val state = calculateState()
        migrationHandler.start()
        effectiveSettings.startState = state
        val changeToApply = state.refactoringsToApply.let { if (rollback) it.reversed() else it }.toMutableList()
        if (changeToApply.isNotEmpty()) {
            if (rollback) {
                changeToApply.add(0, StartMigration())
                changeToApply.addLastButBeforeRefactoringOfType(CreateVersionControlTable::class, EndMigration())
            } else {
                changeToApply.addFirstButAfterRefactoringOfType(CreateVersionControlTable::class, StartMigration())
                changeToApply.add(EndMigration())
            }
        }
        return migrationHandler.wrapInTransactionOnWhen(effectiveSettings.transactionScope == MIGRATION) {
            changeToApply
                .mapIndexed { i, refactoring ->
                    Pair(
                        refactoring,
                        emptyList<ModelChange>() +
                            Comment("Start ${if (rollback) "ROLLBACK" else ""} ${refactoring.id()}\nContains ${refactoring.forward.size} changes") +
                            (
                                if (rollback) {
                                    refactoring.rollback
                                } else {
                                    refactoring.forward
                                }
                                ) +
                            listOfNotNull(
                                logInVersionControl(
                                    i + state.lastExecutionSequence,
                                    refactoring,
                                    state.appliedRefactorings[refactoring.id()],
                                    rollback,
                                ).takeUnless { rollback && refactoring is CreateVersionControlTable },
                            ),
                    )
                }
                .flatMap { (refactoring, modelChanges) ->
                    migrationHandler.wrapInTransactionOnWhen(effectiveSettings.transactionScope == REFACTORING) {
                        migrationHandler.executeRefactoring(refactoring)
                        modelChanges.flatMap { modelChange ->
                            modelChange.lines()
                                .also { lines -> migrationHandler.executeModelChange(modelChange, lines) }
                        }
                    }
                }.also {
                    migrationHandler.end()
                }
        }
    }

    override fun getAppliedRefactorings(): SortedSet<AppliedRefactoring> {
        try {
            return withConnection {
                it.executeQuery<AppliedRefactoring>(
                    "SELECT " +
                        listOf(EXECUTION_ORDER, ID_COLUMN, CHECK_SUM, EXECUTED_SEQUENCE, ROLLED_BACK)
                            .map(::DbIdentifier)
                            .joinToString { d -> d.toSql(effectiveSettings) } +
                        " FROM " +
                        effectiveSettings.versionControlTable.toSql(effectiveSettings),
                ) { rs ->
                    AppliedRefactoring(
                        executionOrder = ExecutionOrder.fromStringValue(rs.getString(1)),
                        id = rs.getString(2),
                        checksum = rs.getString(3),
                        executionSequence = rs.getInt(4),
                        rolledback = rs.getBoolean(5),
                    )
                }.toSortedSet()
            }
        } catch (e: SQLException) {
            sqlLogger.warn("Cannot retrieve current state, assume no control table : " + e.message)
            return sortedSetOf()
        }
    }

    private fun shouldRun(
        currentState: Map<String, AppliedRefactoring>,
        refactoring: Refactoring,
    ): Boolean {
        val previousExecution =
            currentState[refactoring.id()] ?: run { logger.info("Run ${refactoring.id()}"); return true }
        return when (refactoring.executeMode) {
            ALWAYS -> {
                logger.info("Always run ${refactoring.id()}")
                true
            }

            ONCE -> {
                check(previousExecution.checksum == refactoring.checkSum() || previousExecution.rolledback) {
                    "Checksum mismatch for ${refactoring.id()} checksum ${refactoring.checkSum()}!=${previousExecution.checksum}"
                }
                previousExecution.rolledback
            }

            ON_CHANGE -> when {
                previousExecution.checksum != refactoring.checkSum() -> {
                    logger.info("Checksum changed from/to '${previousExecution.checksum}/${refactoring.checkSum()}' so re-running ${refactoring.id()}")
                    true
                }

                else -> false
            }
        }
    }

    private fun logInVersionControl(
        index: Int,
        refactoring: Refactoring,
        appliedRefactoring: AppliedRefactoring?,
        rollback: Boolean,
    ): ModelChange {
        return if (appliedRefactoring != null || rollback) {
            updateRow {
                table(effectiveSettings.versionControlTable)
                set(LAST_APPLIED to ColumnValue.expression(effectiveSettings.now()))
                set(
                    EXECUTION_COUNT to ColumnValue.expression(
                        column(EXECUTION_COUNT).toSql(effectiveSettings) +
                            if (!rollback || refactoring is AStartEndMarker) " + 1" else " - 1",
                    ),
                )
                set(EXECUTED_SEQUENCE to value(index))
                set(MIGRATION_RUN_ID to value(effectiveSettings.migrationRunId))
                set(ROLLED_BACK to value(rollback))
                where {
                    col(ID_COLUMN) eq refactoring.id()
                }
            }
        } else {
            insertRow {
                table(effectiveSettings.versionControlTable)
                values {
                    value(ID_COLUMN, refactoring.id())
                    value(EXECUTION_FREQUENCY, refactoring.executeMode.name)
                    valueExpression(FIRST_APPLIED, effectiveSettings.now())
                    valueExpression(LAST_APPLIED, effectiveSettings.now())
                    value(EXECUTION_ORDER, refactoring.executionOrder.toSingleValue())
                    value(MIGRATION_RUN_ID, effectiveSettings.migrationRunId)
                    value(EXECUTED_SEQUENCE, index)
                    value(ROLLED_BACK, false)
                    value(EXECUTION_COUNT, 1)
                    value(CHECK_SUM, refactoring.checkSum())
                }
            }
        }
    }

    private fun ModelChange.lines(): List<String> {
        return templateResolver.findTemplate(this)?.convert(this)?.filterNotNull() ?: emptyList()
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
                singleOf(::ApplyDirectlyMigrationHandler).onClose { it?.close() }
                singleOf(::WriteChangesToFileMigrationHandler)
                single { KontrolDbEngine(getAll(), get(), get(), get(), get()) }
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

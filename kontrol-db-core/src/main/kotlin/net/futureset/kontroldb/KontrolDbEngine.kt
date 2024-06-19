package net.futureset.kontroldb

import net.futureset.kontroldb.migration.ApplyDirectlyMigrationHandler
import net.futureset.kontroldb.migration.MigrationHandler
import net.futureset.kontroldb.migration.WriteChangesToFileMigrationHandler
import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.ColumnValue.Companion.column
import net.futureset.kontroldb.model.ColumnValue.Companion.value
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.modelchange.ChangeToDefaultCatalogAndSchema
import net.futureset.kontroldb.modelchange.CommentMarker
import net.futureset.kontroldb.modelchange.InitCatalog
import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.modelchange.InsertRows.InsertRowsBuilder.Companion.insertRowsInto
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.modelchange.UpdateRows.UpdateRowsBuilder.Companion.updateRowsOf
import net.futureset.kontroldb.refactoring.APPLICATION_VERSION
import net.futureset.kontroldb.refactoring.AStartEndMarker
import net.futureset.kontroldb.refactoring.AppliedRefactoring
import net.futureset.kontroldb.refactoring.CHECK_SUM
import net.futureset.kontroldb.refactoring.CreateVersionControlTable
import net.futureset.kontroldb.refactoring.EXECUTED_SEQUENCE
import net.futureset.kontroldb.refactoring.EXECUTION_COUNT
import net.futureset.kontroldb.refactoring.EXECUTION_FREQUENCY
import net.futureset.kontroldb.refactoring.EXECUTION_ORDER
import net.futureset.kontroldb.refactoring.EndMigration
import net.futureset.kontroldb.refactoring.ExecuteMode.ALWAYS
import net.futureset.kontroldb.refactoring.ExecuteMode.ONCE
import net.futureset.kontroldb.refactoring.ExecuteMode.ON_CHANGE
import net.futureset.kontroldb.refactoring.ExecutionOrder
import net.futureset.kontroldb.refactoring.FIRST_APPLIED
import net.futureset.kontroldb.refactoring.ID_COLUMN
import net.futureset.kontroldb.refactoring.LAST_APPLIED
import net.futureset.kontroldb.refactoring.MIGRATION_RUN_ID
import net.futureset.kontroldb.refactoring.ROLLED_BACK
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.refactoring.StartMigration
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.settings.TransactionScope.MIGRATION
import net.futureset.kontroldb.settings.TransactionScope.REFACTORING
import net.futureset.kontroldb.template.TemplateResolver
import org.koin.core.context.stopKoin
import org.slf4j.LoggerFactory
import java.net.URI
import java.nio.file.Path
import java.sql.SQLException
import java.util.SortedSet
import java.util.jar.Manifest
import kotlin.reflect.KClass

data class KontrolDbEngine(
    private val allRefactoring: List<Refactoring>,
    val effectiveSettings: EffectiveSettings,
    private val templateResolver: TemplateResolver,
    val applySqlDirectly: ApplyDirectlyMigrationHandler,
    val applySqlToScript: WriteChangesToFileMigrationHandler,
) : KontrolDbCommands, AutoCloseable {

    val refactorings = allRefactoring.toSortedSet()

    val version: String by lazy {

        val uri = javaClass.getResource(javaClass.simpleName + ".class")?.toURI()
        if (uri?.scheme == "jar") {
            val manifestUrl = URI.create(uri.toASCIIString().split("!").first() + "!/META-INF/MANIFEST.MF").toURL()
            manifestUrl.openStream().use {
                Manifest(it).mainAttributes.getValue("Implementation-Version")
            }
        } else {
            "unknown"
        }
    }

    private val resourceResolver = ResourceResolver(effectiveSettings.externalFileRoot)

    init {
        effectiveSettings.templateResolver = templateResolver
        require(refactorings.size == allRefactoring.size) {
            "Each refactoring must be unique"
        }
    }

    private val logger = LoggerFactory.getLogger(KontrolDbEngine::class.java)

    override fun generateSql(outputDirectory: Path) {
        applySqlToScript.outputDirectory = outputDirectory
        generateChanges(applySqlToScript, false)
    }

    override fun generateSqlRollback(outputDirectory: Path) {
        applySqlToScript.outputDirectory = outputDirectory
        generateChanges(applySqlToScript, true)
    }

    override fun applySql(): Int {
        return generateChanges(applySqlDirectly, false).size
    }

    override fun getNextRefactorings(): List<Refactoring> {
        return calculateState().refactoringsToApply
    }

    private fun initialiseDatabase() {
        listOfNotNull(
            effectiveSettings.defaultCatalog?.let(::InitCatalog),
            effectiveSettings.defaultSchema?.let(::InitSchema),
            effectiveSettings.defaultSchema?.let { ChangeToDefaultCatalogAndSchema() },
        ).onEach { modelChange ->
            val t = templateResolver.findTemplate(modelChange)
            applySqlDirectly.executeModelChange(modelChange, t?.convert(modelChange)?.filterNotNull().orEmpty())
        }
    }

    private fun calculateState(): KontrolDbState {
        initialiseDatabase()
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
                        (
                            emptyList<ModelChange>() +
                                ScriptComment("Start ${if (rollback) "ROLLBACK" else ""} ${refactoring.id()}\nContains ${refactoring.forward.size} changes") +
                                (
                                    if (rollback) {
                                        refactoring.rollback
                                    } else {
                                        refactoring.forward
                                    }
                                    ) +
                                ChangeToDefaultCatalogAndSchema() +
                                listOfNotNull(
                                    logInVersionControl(
                                        i + state.lastExecutionSequence,
                                        refactoring,
                                        state.appliedRefactorings[refactoring.id()],
                                        rollback,
                                    ).takeUnless { rollback && refactoring is CreateVersionControlTable },
                                )
                            ),
                    )
                }
                .flatMap { (refactoring, modelChanges) ->
                    migrationHandler.wrapInTransactionOnWhen(effectiveSettings.transactionScope == REFACTORING) {
                        migrationHandler.executeRefactoring(refactoring)
                        modelChanges.fold(mutableListOf(), this::moveCommentTextOntoNextChange)
                            .flatMap { (modelChange, l) ->
                                l.also { lines ->
                                    migrationHandler.executeModelChange(
                                        modelChange,
                                        lines.map {
                                            it.trim() + effectiveSettings.statementSeparator
                                        },
                                    )
                                }
                            }
                    }
                }
        }.also {
            migrationHandler.end()
        }
    }

    private fun moveCommentTextOntoNextChange(
        modelToStatementsSoFar: MutableList<Pair<ModelChange, List<String>>>,
        currentModelChange: ModelChange,
    ): MutableList<Pair<ModelChange, List<String>>> {
        val previousStatementIfComment = modelToStatementsSoFar.lastOrNull()?.takeIf { it.first is CommentMarker }
        if (previousStatementIfComment != null) {
            modelToStatementsSoFar.add(
                Pair(
                    currentModelChange,
                    currentModelChange.lines()
                        .mapIndexed { i, s ->
                            if (i == 0) {
                                previousStatementIfComment.second.joinToString(
                                    separator = "\n",
                                    postfix = "\n",
                                ) + s
                            } else {
                                s
                            }
                        },
                ),
            )
            modelToStatementsSoFar[modelToStatementsSoFar.size - 2] =
                Pair(previousStatementIfComment.first, emptyList())
        } else {
            modelToStatementsSoFar.add(Pair(currentModelChange, currentModelChange.lines()))
        }
        return modelToStatementsSoFar
    }

    override fun getAppliedRefactorings(): SortedSet<AppliedRefactoring> {
        try {
            return applySqlDirectly.withConnection {
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
                check(previousExecution.checksum == refactoring.checkSum(resourceResolver) || previousExecution.rolledback) {
                    "Checksum mismatch for ${refactoring.id()} checksum ${refactoring.checkSum(resourceResolver)}!=${previousExecution.checksum}"
                }
                previousExecution.rolledback
            }

            ON_CHANGE -> when {
                previousExecution.checksum != refactoring.checkSum(resourceResolver) -> {
                    logger.info(
                        "Checksum changed from/to '${previousExecution.checksum}/${
                            refactoring.checkSum(
                                resourceResolver,
                            )
                        }' so re-running ${refactoring.id()}",
                    )
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
            updateRowsOf(effectiveSettings.versionControlTable) {
                set(LAST_APPLIED to ColumnValue.expression(effectiveSettings.dbNowTimestamp()))
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
            insertRowsInto(effectiveSettings.versionControlTable) {
                row {
                    value(ID_COLUMN, refactoring.id())
                    value(APPLICATION_VERSION, version)
                    value(EXECUTION_FREQUENCY, refactoring.executeMode.name)
                    valueExpression(FIRST_APPLIED, effectiveSettings.dbNowTimestamp())
                    valueExpression(LAST_APPLIED, effectiveSettings.dbNowTimestamp())
                    value(EXECUTION_ORDER, refactoring.executionOrder.toSingleValue())
                    value(MIGRATION_RUN_ID, effectiveSettings.migrationRunId)
                    value(EXECUTED_SEQUENCE, index)
                    value(ROLLED_BACK, false)
                    value(EXECUTION_COUNT, 1)
                    value(CHECK_SUM, refactoring.checkSum(resourceResolver))
                }
            }
        }
    }

    private fun ModelChange.lines(): List<String> {
        return templateResolver.findTemplate(this)?.convert(this)?.filterNotNull() ?: emptyList()
    }

    override fun close() {
        try {
            applySqlDirectly.close()
        } finally {
            stopKoin()
        }
    }
}

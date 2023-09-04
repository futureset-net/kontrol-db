package net.futureset.kmigrate

import net.futureset.kmigrate.modelchange.InsertRowBuilder.Companion.insertRow
import net.futureset.kmigrate.modelchange.UpdateRowBuilder.Companion.updateRow
import net.futureset.kmigrate.settings.EffectiveSettings
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.util.SortedSet
import kotlin.io.path.writeText
import kotlin.reflect.KClass

@DslMarker
annotation class KMigrateDslMarker

data class KMigrate(
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

    private val logger = LoggerFactory.getLogger(KMigrate::class.java)

    fun generateSql(output: Path) {
        output.parent?.let(Files::createDirectories)
        output.writeText(generateSql().joinToString(separator = "\ngo\n\n", postfix = "\ngo\n"))
    }

    fun getCurrentState(): SortedSet<AppliedRefactoring> {
        return sqlExecutor.getCurrentState()
    }

    fun applySql(): Int {
        val sqlToApply = generateSql()
        sqlExecutor.executeAll(sqlToApply)
        return sqlToApply.size
    }

    private fun generateSql(): List<String> {
        val currentState = sqlExecutor.getCurrentState().associateBy(AppliedRefactoring::id)
        val lastSequence = currentState.values.maxOfOrNull { it.executionSequence } ?: 1
        return refactorings.toSortedSet()
            .filter { shouldRun(currentState, it) }
            .toList()
            .flatMapIndexed { i, refactoring ->
                refactoring.forward + logInVersionControl(i + lastSequence, refactoring, currentState[refactoring.id()])
            }.map { modelChange ->
                requireNotNull(templatesByType[modelChange::class]?.first(SqlTemplate<ModelChange>::canApply)) {
                    "No template found for ${modelChange.getName()}"
                }.convert(modelChange)
            }
    }

    private fun shouldRun(
        currentState: Map<String, AppliedRefactoring>,
        refactoring: Refactoring,
    ): Boolean {
        val previousExecution = currentState[refactoring.id()] ?: return true
        return when (refactoring.executeMode) {
            ExecuteMode.ALWAYS -> { logger.info("Always run ${refactoring.id()}"); true }
            ExecuteMode.ONCE -> {
                require(previousExecution.checksum == refactoring.checkSum()) {
                    "Checksum mismatch for ${refactoring.id()} checksum ${refactoring.checkSum()}!=${previousExecution.checksum}"
                }; false
            }
            ExecuteMode.ON_CHANGE -> (previousExecution.checksum != refactoring.checkSum()).apply {
                if (this) {
                    logger.info("Checksum changed for so re-running ${refactoring.id()}")
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
                setValue("EXECUTION_SEQUENCE", index)
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
            value("EXECUTION_SEQUENCE", index)
            value("EXECUTION_COUNT", 1)
            value("CHECK_SUM", refactoring.checkSum())
        }
    }
}

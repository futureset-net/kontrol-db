package net.futureset.kontroldb.migration

import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.refactoring.Refactoring
import net.futureset.kontroldb.settings.EffectiveSettings
import java.nio.file.Path
import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.notExists
import kotlin.io.path.writeText

class WriteChangesToFileMigrationHandler(
    private val effectiveSettings: EffectiveSettings,
) : MigrationHandler {

    private var allLines = mutableListOf<String>()
    private val transactionId = AtomicInteger()
    private var transactionDepth = 0
    lateinit var outputDirectory: Path
    override fun start() {
        require(outputDirectory.notExists() || outputDirectory.isDirectory())
        outputDirectory.createDirectories()
        allLines.clear()
        transactionId.set(0)
    }

    override fun end() {
        val line = "-".repeat(80) + effectiveSettings.operatingSystem.lineSeparator
        allLines.add(line)
        effectiveSettings.changeScript(allLines, "kontrol-db")
        outputDirectory.resolve("output.sql").writeText(
            allLines.joinToString(
                prefix = line,
                separator = effectiveSettings.operatingSystem.lineSeparator,
            ),
        )
    }

    override fun executeModelChange(change: ModelChange, rawChanges: List<String>) {
        allLines.addAll(rawChanges)
    }

    override fun executeRefactoring(refactoring: Refactoring) {
    }

    override fun <T> wrapInTransactionOnWhen(predicate: Boolean, lambda: () -> T): T {
        return if (predicate) {
            val id = transactionId.incrementAndGet()
            try {
                transactionDepth++
                allLines.add(effectiveSettings.startTransaction(id))
                lambda()
            } finally {
                allLines.add(effectiveSettings.endTransaction(id) + effectiveSettings.batchSeparator)
                transactionDepth--
            }
        } else {
            lambda()
        }
    }
}

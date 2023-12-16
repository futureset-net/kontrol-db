package net.futureset.kontroldb

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import net.futureset.kontroldb.refactoring.AppliedRefactoring
import net.futureset.kontroldb.refactoring.Refactoring
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.DefinitionOptions
import org.koin.core.module.dsl.new
import org.koin.core.module.dsl.onOptions
import org.koin.dsl.bind
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.util.SortedSet
import kotlin.concurrent.thread
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.io.path.writeText

fun String.executeAsShell(): Int = run {
    println(this)
    ProcessBuilder()
        .redirectErrorStream(true).apply {
            if (OperatingSystem.current() == OperatingSystem.WINDOWS) {
                command("cmd.exe", "/c", this@executeAsShell)
            } else {
                command("/usr/bin/env", "bash", "-c", this@executeAsShell)
            }
        }
        .start()
        .also {
            thread(start = true, isDaemon = true) {
                it.inputStream.reader().useLines { seq ->
                    seq.forEach(::println)
                }
            }
        }
        .waitFor()
}

fun Path.toStringWithNumberedLines(): String =
    this.readLines().mapIndexed { index, s -> "$index".padStart(4, '0') + " $s" }.joinToString(separator = "\n")

fun Path.readDsvToMapList(delimiter: String = "|"): List<Map<String, String>> {
    return Files.newBufferedReader(this).use {
        val headers = it.readLine().split(delimiter)
        it.lineSequence()
            .map { line ->
                line.split(delimiter).mapIndexed { index, column -> Pair(headers[index], column) }.associate { it }
            }.toList()
    }
}

fun List<Refactoring>.simpleNames(): List<String> {
    return this.map { it.id().split(".").last() }
}

fun SortedSet<AppliedRefactoring>.simpleNames(): List<String> {
    return this.map { it.id.split(".").last() }
}

fun Path.replaceText(search: String, replace: String) {
    val def = this.readText()
    val newDef = def.replace(search, replace)
    this.writeText(newDef)
}

fun capturingLogEvents(lambda: () -> Unit): List<ILoggingEvent> {
    val logContext = LoggerFactory.getILoggerFactory() as LoggerContext
    val listAppender = ListAppender<ILoggingEvent>()
    val rootLogger = logContext.getLogger(Logger.ROOT_LOGGER_NAME)
    rootLogger.addAppender(listAppender)
    listAppender.start()
    try {
        lambda()
    } finally {
        listAppender.stop()
        rootLogger.detachAppender(listAppender)
    }
    return listAppender.list
}

inline fun <reified R : Refactoring> Module.refactoring(
    crossinline constructor: () -> R,
    noinline options: DefinitionOptions<R>? = null,
): KoinDefinition<R> = single { new(constructor) }.onOptions(options).also { it.bind<Refactoring>() }

inline fun <reified R : Refactoring, reified T1> Module.refactoring(
    crossinline constructor: (T1) -> R,
    noinline options: DefinitionOptions<R>? = null,
): KoinDefinition<R> = single { new(constructor) }.onOptions(options).also { it.bind<Refactoring>() }

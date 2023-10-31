package net.futureset.kontroldb

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import net.futureset.kontroldb.refactoring.AppliedRefactoring
import net.futureset.kontroldb.refactoring.Refactoring
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.util.SortedSet
import kotlin.concurrent.thread
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.io.path.writeText

fun String.executeAsShell() = run {
    println(this)
    ProcessBuilder()
        .redirectErrorStream(true).apply {
            if (OperatingSystem.current() == OperatingSystem.WINDOWS) {
                command("cmd.exe", "/c")
            } else {
                command("/usr/bin/env", "bash", "-c", this@executeAsShell)
            }
        }
        .start().also {
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

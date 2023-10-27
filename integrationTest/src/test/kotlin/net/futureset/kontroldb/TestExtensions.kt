package net.futureset.kontroldb

import java.nio.file.Files
import java.nio.file.Path
import java.util.SortedSet
import kotlin.concurrent.thread
import kotlin.io.path.readLines

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
                it.inputReader().useLines { seq ->
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
            .map { line -> line.split(delimiter).mapIndexed { index, column -> Pair(headers[index], column) }.associate { it } }.toList()
    }
}

fun List<Refactoring>.simpleNames(): List<String> {
    return this.map { it.id().split(".").last() }
}

fun SortedSet<AppliedRefactoring>.simpleNames(): List<String> {
    return this.map { it.id.split(".").last() }
}

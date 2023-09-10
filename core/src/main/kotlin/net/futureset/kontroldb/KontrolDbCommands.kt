package net.futureset.kontroldb

import java.nio.file.Path
import java.util.SortedSet

interface KontrolDbCommands {

    fun generateSql(output: Path)
    fun applySql(): Int
    fun getCurrentState(): SortedSet<AppliedRefactoring>
    fun getNextRefactorings(): List<Refactoring>
}

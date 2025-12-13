package net.futureset.kontroldb

import net.futureset.kontroldb.refactoring.AppliedRefactoring
import net.futureset.kontroldb.refactoring.Refactoring
import java.nio.file.Path
import java.util.SortedSet

interface KontrolDbCommands {
    fun generateSql(outputDirectory: Path)

    fun applySql(): Int

    fun getAppliedRefactorings(): SortedSet<AppliedRefactoring>

    fun getNextRefactorings(): List<Refactoring>

    fun generateSqlRollback(outputDirectory: Path)
}

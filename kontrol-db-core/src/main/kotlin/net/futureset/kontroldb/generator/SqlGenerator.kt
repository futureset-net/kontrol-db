package net.futureset.kontroldb.generator

import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

interface SqlGenerator<T : ModelChange> : Comparable<SqlGenerator<T>> {

    var sqlGeneratorResolver: SqlGeneratorResolver
    val priority: GeneratorPriority
    val effectiveSettings: EffectiveSettings

    fun type(): KClass<T>
    override fun compareTo(other: SqlGenerator<T>): Int {
        return priority.compareTo(other.priority)
    }

    fun <U : ModelChange> template(t: U): SqlGenerator<U>? {
        return sqlGeneratorResolver.resolveGenerator(t)
    }
    fun canApply() = true

    fun Collection<DbIdentifier>.columnNames(): String = joinToString(", ") { it.toQuoted(effectiveSettings) }

    fun convert(change: T): List<String?>
}

fun String.trimBlankLines() = this.replace(Regex("(?:\\s*\r?\n){2,}"), "\n")

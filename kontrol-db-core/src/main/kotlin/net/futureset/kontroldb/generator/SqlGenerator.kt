package net.futureset.kontroldb.generator

import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

interface SqlGenerator<T : ModelChange> : Prioritised {
    val es: EffectiveSettings
    val type: KClass<T>

    fun canApply() = true

    fun Collection<DbIdentifier>.columnNames(): String = joinToString(", ") { it.toQuoted(es) }

    fun convert(change: T): List<String?>
}

fun String.trimBlankLines() = this.replace(Regex("(?:\\s*\r?\n){2,}"), "\n")

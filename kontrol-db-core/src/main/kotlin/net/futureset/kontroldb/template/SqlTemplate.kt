package net.futureset.kontroldb.template

import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

interface SqlTemplate<T : ModelChange> : Comparable<SqlTemplate<T>> {

    var templateResolver: TemplateResolver
    val priority: TemplatePriority
    val effectiveSettings: EffectiveSettings

    fun type(): KClass<T>
    override fun compareTo(other: SqlTemplate<T>): Int {
        return priority.compareTo(other.priority)
    }

    fun <U : ModelChange> template(t: U): SqlTemplate<U>? {
        return templateResolver.findTemplate(t)
    }
    fun canApply() = true

    fun Collection<DbIdentifier>.columnNames(): String = joinToString(", ") { it.toSql(effectiveSettings) }

    fun convert(change: T): List<String?>
}

fun String.trimBlankLines() = this.replace(Regex("(?:\\s*\r?\n){2,}"), "\n")

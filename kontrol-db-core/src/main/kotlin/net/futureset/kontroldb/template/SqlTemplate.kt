package net.futureset.kontroldb.template

import net.futureset.kontroldb.modelchange.ModelChange
import kotlin.reflect.KClass

interface SqlTemplate<T : ModelChange> : Comparable<SqlTemplate<T>> {

    var templateResolver: TemplateResolver
    val priority: TemplatePriority

    fun type(): KClass<T>
    override fun compareTo(other: SqlTemplate<T>): Int {
        return priority.compareTo(other.priority)
    }

    fun <U : ModelChange> template(t: U): SqlTemplate<U>? {
        return templateResolver.findTemplate(t)
    }
    fun canApply() = true

    fun convert(change: T): List<String?>
}

fun String.trimBlankLines() = this.replace(Regex("(?:\\s*\r?\n){2,}"), "\n")
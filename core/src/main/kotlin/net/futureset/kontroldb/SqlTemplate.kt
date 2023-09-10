package net.futureset.kontroldb

import kotlin.reflect.KClass

interface SqlTemplate<T : ModelChange> : Comparable<SqlTemplate<T>> {

    val priority: TemplatePriority

    fun type(): KClass<T>
    override fun compareTo(other: SqlTemplate<T>): Int {
        return priority.compareTo(other.priority)
    }

    fun canApply() = true

    fun convert(change: T): List<String?>
}

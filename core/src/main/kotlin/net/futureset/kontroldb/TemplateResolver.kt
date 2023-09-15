package net.futureset.kontroldb

import java.util.SortedSet
import kotlin.reflect.KClass

class TemplateResolver(templates: List<SqlTemplate<ModelChange>>) {

    private val templatesByType = HashMap<KClass<out ModelChange>, SortedSet<SqlTemplate<ModelChange>>>()

    init {
        require(templates.isNotEmpty()) { "Could not find any templates" }
        templates.forEach {
            templatesByType.computeIfAbsent(it.type()) { sortedSetOf() }.add(it)
        }
    }

    fun <U : ModelChange> findTemplate(modelChange: U): SqlTemplate<U>? {
        @Suppress("UNCHECKED_CAST")
        return requireNotNull(templatesByType[modelChange::class] as SortedSet<SqlTemplate<U>>?) {
            "No template found for ${modelChange.getName()}"
        }.firstOrNull(SqlTemplate<U>::canApply)
    }
}

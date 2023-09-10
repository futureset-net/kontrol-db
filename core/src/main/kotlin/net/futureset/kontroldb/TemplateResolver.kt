package net.futureset.kontroldb

import java.util.SortedSet
import kotlin.reflect.KClass

class TemplateResolver(templates: List<SqlTemplate<ModelChange>>) {

    private val templatesByType = HashMap<KClass<ModelChange>, SortedSet<SqlTemplate<ModelChange>>>()

    init {
        require(templates.isNotEmpty()) { "Could not find any templates" }
        templates.forEach {
            templatesByType.computeIfAbsent(it.type()) { sortedSetOf() }.add(it)
        }
    }

    fun findTemplateForSettings(modelChange: ModelChange): SqlTemplate<ModelChange>? {
        return requireNotNull(templatesByType[modelChange::class]) {
            "No template found for ${modelChange.getName()}"
        }.firstOrNull(SqlTemplate<ModelChange>::canApply)
    }
}

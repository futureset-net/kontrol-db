package net.futureset.kontroldb.generator

import net.futureset.kontroldb.modelchange.ModelChange
import java.util.SortedSet
import kotlin.reflect.KClass

class SqlGeneratorResolver(templates: List<SqlGenerator<ModelChange>>) {

    private val templatesByType = HashMap<KClass<out ModelChange>, SortedSet<SqlGenerator<ModelChange>>>()

    init {
        require(templates.isNotEmpty()) { "Could not find any templates" }
        templates.forEach {
            templatesByType.computeIfAbsent(it.type) { sortedSetOf() }.add(it)
        }
    }

    fun <U : ModelChange> resolveGenerator(modelChange: U): SqlGenerator<U>? {
        @Suppress("UNCHECKED_CAST")
        return requireNotNull(templatesByType[modelChange::class] as SortedSet<SqlGenerator<U>>?) {
            "No template found for ${modelChange.getName()}"
        }.firstOrNull(SqlGenerator<U>::canApply)
    }
}

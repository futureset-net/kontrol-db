package net.futureset.kontroldb.generator

import net.futureset.kontroldb.modelchange.ModelChange
import java.util.SortedSet
import kotlin.reflect.KClass

class SqlGeneratorFactory {

    private val templatesByType = HashMap<KClass<out ModelChange>, SortedSet<SqlGenerator<ModelChange>>>()

    fun register(generator: SqlGenerator<ModelChange>) {
        templatesByType.computeIfAbsent(generator.type) { sortedSetOf() }.add(generator)
    }

    private fun <U : ModelChange> resolveGenerator(modelChange: U): SqlGenerator<U>? {
        @Suppress("UNCHECKED_CAST")
        return requireNotNull(templatesByType[modelChange::class] as SortedSet<SqlGenerator<U>>?) {
            "No template found for ${modelChange.getName()}"
        }.firstOrNull(SqlGenerator<U>::canApply)
    }

    fun generateSql(change: ModelChange): List<String> {
        return resolveGenerator(change)?.convert(change)?.filterNotNull() ?: emptyList()
    }

    fun generateSqlSingle(change: ModelChange): String {
        val result = generateSql(change)
        require(result.size < 2) { "Expected single result for sql $change, got ${result.size}" }
        return result.firstOrNull() ?: ""
    }
}

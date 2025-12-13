package net.futureset.kontroldb.dsl

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.modelchange.DatabaseConditionalChange
import net.futureset.kontroldb.modelchange.ModelChange

@KontrolDbDslMarker
data class ModelChangesBuilder(
    val changes: MutableList<ModelChange> = mutableListOf(),
) : Builder<ModelChangesBuilder, List<ModelChange>> {
    override fun build(): List<ModelChange> = changes.toList()

    /**
     * For onlyIfDatabase
     *
     * @param dbPredicate expression to match database name
     * @receiver [ModelChange] used to modify a change within a refactoring
     * @return [DatabaseConditionalChange] wrap change in condition
     */
    fun ModelChange.onlyIfDatabase(dbPredicate: (String) -> Boolean): DatabaseConditionalChange {
        val conditionalChange = DatabaseConditionalChange(dbPredicate, this)
        if (changes.lastOrNull() == this) {
            changes.removeLast()
            changes.add(conditionalChange)
        }
        return conditionalChange
    }
}

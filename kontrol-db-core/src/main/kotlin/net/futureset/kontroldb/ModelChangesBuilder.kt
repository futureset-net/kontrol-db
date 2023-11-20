package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.ModelChange

@KontrolDbDslMarker
data class ModelChangesBuilder(
    val changes: MutableList<ModelChange> = mutableListOf(),
) : Builder<ModelChangesBuilder, List<ModelChange>> {

    override fun build(): List<ModelChange> {
        return changes.toList()
    }
}

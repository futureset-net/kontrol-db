package net.futureset.kontroldb

@KontrolDbDslMarker
data class ModelChangesBuilder(
    val changes: MutableList<ModelChange> = mutableListOf(),
) : Builder<ModelChangesBuilder, List<ModelChange>> {

    override fun build(): List<ModelChange> {
        return changes.toList()
    }
}

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier

data class DropRole(
    val roleName: DbIdentifier,
) : ModelChange

fun ModelChangesBuilder.dropRole(name: String): DropRole =
    DropRole(roleName = DbIdentifier(name)).apply(changes::add)

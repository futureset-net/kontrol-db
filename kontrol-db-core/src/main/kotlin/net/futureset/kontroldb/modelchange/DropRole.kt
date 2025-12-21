package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.DbIdentifier

data class DropRole(
    val roleName: DbIdentifier,
) : ModelChange

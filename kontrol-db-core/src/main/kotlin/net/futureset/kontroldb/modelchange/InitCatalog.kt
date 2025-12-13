package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.DbIdentifier

data class InitCatalog(
    val catalog: DbIdentifier,
) : ModelChange

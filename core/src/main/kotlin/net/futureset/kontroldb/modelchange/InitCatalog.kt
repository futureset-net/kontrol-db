package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange

data class InitCatalog(val catalog: DbIdentifier) : ModelChange

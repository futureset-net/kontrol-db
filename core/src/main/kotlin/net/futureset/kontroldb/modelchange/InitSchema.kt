package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange

data class InitSchema(val schema: DbIdentifier) : ModelChange

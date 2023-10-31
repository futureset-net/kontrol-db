package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.DbIdentifier

data class InitSchema(val schema: DbIdentifier) : ModelChange

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.DbIdentifier

interface ConstraintModelChange : ModelChange {
    val constraintName: DbIdentifier?
}

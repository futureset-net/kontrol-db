package net.futureset.kontroldb

interface ConstraintModelChange : ModelChange {

    val constraintName: DbIdentifier?
}

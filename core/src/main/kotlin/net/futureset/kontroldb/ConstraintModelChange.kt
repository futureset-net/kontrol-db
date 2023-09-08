package net.futureset.kontroldb

interface ConstraintModelChange : ModelChange {

    var constraintName: DbIdentifier?
}

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier

data class DropRole(
    val roleName: DbIdentifier,
) : ModelChange

/**
 * Drop a database role
 *
 * @param roleName name of the role to drop
 * @return [DropRole] the immutable role data
 *
 * @receiver [ModelChangesBuilder] a container for a collection of model changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createRole
 */
fun ModelChangesBuilder.dropRole(roleName: String): DropRole =
    DropRole(roleName = DbIdentifier(roleName)).apply(changes::add)

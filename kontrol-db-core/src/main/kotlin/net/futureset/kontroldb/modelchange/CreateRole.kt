package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier

data class CreateRole(
    val roleName: DbIdentifier,
) : ModelChange {
    @KontrolDbDslMarker
    data class CreateRoleBuilder(
        private var roleName: DbIdentifier? = null,
    ) : Builder<CreateRoleBuilder, CreateRole> {
        fun roleName(name: String) = apply {
            this.roleName = DbIdentifier(name)
        }

        override fun build(): CreateRole = CreateRole(
            roleName = requireNotNull(roleName),
        )
    }
}

/**
 * Create a database role
 *
 * @param roleName name of the role to create
 * @return [CreateRole] the immutable role data
 *
 * @receiver [ModelChangesBuilder] a container for a collection of model changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createRole
 */
fun ModelChangesBuilder.createRole(roleName: String): CreateRole = CreateRole
    .CreateRoleBuilder()
    .roleName(roleName)
    .build()
    .apply(changes::add)

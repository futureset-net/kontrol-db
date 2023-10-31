package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChangesBuilder
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
        override fun build(): CreateRole {
            return CreateRole(
                roleName = requireNotNull(roleName),
            )
        }
    }
}
fun ModelChangesBuilder.createRole(lambda: CreateRole.CreateRoleBuilder.() -> Unit): CreateRole =
    CreateRole.CreateRoleBuilder().apply(lambda).build().apply(changes::add)

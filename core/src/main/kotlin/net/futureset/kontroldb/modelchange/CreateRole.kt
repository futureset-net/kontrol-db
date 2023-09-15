package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange

data class CreateRole(
    val roleName: DbIdentifier,
) : ModelChange {

    data class CreateRoleBuilder(
        private var roleName: DbIdentifier? = null,
    ) : Builder<CreateRole> {

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

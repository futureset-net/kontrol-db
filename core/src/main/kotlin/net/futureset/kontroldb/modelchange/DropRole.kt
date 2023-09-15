package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange

data class DropRole(
    val roleName: DbIdentifier,
) : ModelChange {

    data class DropRoleBuilder(
        var roleName: DbIdentifier? = null,
    ) : Builder<DropRole> {

        fun roleName(name: String) = apply {
            this.roleName = DbIdentifier(name)
        }

        override fun build(): DropRole {
            return DropRole(
                roleName = requireNotNull(roleName),
            )
        }
    }
}

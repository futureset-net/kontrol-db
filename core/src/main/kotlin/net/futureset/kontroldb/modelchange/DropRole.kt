package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder

data class DropRole(
    val roleName: DbIdentifier,
) : ModelChange {

    @KontrolDbDslMarker
    data class DropRoleBuilder(
        var roleName: DbIdentifier? = null,
    ) : Builder<DropRoleBuilder, DropRole> {

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

fun ModelChangesBuilder.dropRole(lambda: DropRole.DropRoleBuilder.() -> Unit): DropRole =
    DropRole.DropRoleBuilder().apply(lambda).build().apply(changes::add)

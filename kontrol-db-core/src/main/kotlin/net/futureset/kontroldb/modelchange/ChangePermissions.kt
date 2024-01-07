package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

enum class GrantOrRevoke {
    GRANT,
    REVOKE,
}
data class ChangePermissions(
    val grantOrRevoke: GrantOrRevoke,
    val grantees: Set<DbIdentifier>,
    val permissions: Set<String>,
    val targetObject: SchemaObject,
    val targetObjectType: String,
) : ModelChange {

    @KontrolDbDslMarker
    class ChangePermissionsBuilder(
        grantOrRevoke: GrantOrRevoke,
        permissions: Set<String>,
    ) : Builder<ChangePermissionsBuilder, ChangePermissions> {

        private var changePermissions = ChangePermissions(
            grantOrRevoke = grantOrRevoke,
            grantees = emptySet(),
            permissions = permissions,
            targetObject = SchemaObject(name = DbIdentifier("")),
            targetObjectType = DbObjectType.TABLE.name,
        )
        fun to(vararg grantees: String) = apply {
            changePermissions = changePermissions.copy(grantees = grantees.map(::DbIdentifier).toSet())
        }

        fun on(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) {
            changePermissions = changePermissions.copy(
                targetObject = changePermissions.targetObject.let(::SchemaObjectBuilder)
                    .apply { name?.let(::name) }.apply(block).build(),
            )
        }

        fun objectType(objectType: String) = apply {
            changePermissions = changePermissions.copy(targetObjectType = objectType)
        }

        fun objectType(objectType: DbObjectType) = apply {
            objectType(objectType.identifier())
        }

        override fun build(): ChangePermissions {
            return changePermissions
        }
    }
}

fun ModelChangesBuilder.grantPermissions(permission: String, vararg permissions: String, lambda: ChangePermissions.ChangePermissionsBuilder.() -> Unit): ChangePermissions =
    ChangePermissions.ChangePermissionsBuilder(GrantOrRevoke.GRANT, arrayListOf(permission, *permissions).toSet())
        .apply(lambda).build().apply(changes::add)

fun ModelChangesBuilder.revokePermissions(permission: String, vararg permissions: String, lambda: ChangePermissions.ChangePermissionsBuilder.() -> Unit): ChangePermissions =
    ChangePermissions.ChangePermissionsBuilder(GrantOrRevoke.REVOKE, arrayListOf(permission, *permissions).toSet())
        .apply(lambda).build().apply(changes::add)

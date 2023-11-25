package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class GrantPermissions(
    val grantees: Set<DbIdentifier>,
    val permissions: Set<String>,
    val targetObject: SchemaObject,
    val targetObjectType: String,
) : ModelChange {

    @KontrolDbDslMarker
    class GrantPermissionBuilder(
        permissions: Set<String>,
    ) : Builder<GrantPermissionBuilder, GrantPermissions> {

        private var grantPermissions = GrantPermissions(
            grantees = emptySet(),
            permissions = permissions,
            targetObject = SchemaObject(name = DbIdentifier("")),
            targetObjectType = DbObjectType.TABLE.name,
        )
        fun to(vararg grantees: String) = apply {
            grantPermissions = grantPermissions.copy(grantees = grantees.map(::DbIdentifier).toSet())
        }

        fun on(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) {
            grantPermissions = grantPermissions.copy(
                targetObject = grantPermissions.targetObject.let(::SchemaObjectBuilder)
                    .apply { name?.let(::name) }.apply(block).build(),
            )
        }

        fun objectType(objectType: String) = apply {
            grantPermissions = grantPermissions.copy(targetObjectType = objectType)
        }

        fun objectType(objectType: DbObjectType) = apply {
            objectType(objectType.identifier())
        }

        override fun build(): GrantPermissions {
            return grantPermissions
        }
    }
}

fun ModelChangesBuilder.grantPermissions(permission: String, vararg permissions: String, lambda: GrantPermissions.GrantPermissionBuilder.() -> Unit): GrantPermissions =
    GrantPermissions.GrantPermissionBuilder(arrayListOf(permission, *permissions).toSet())
        .apply(lambda).build().apply(changes::add)

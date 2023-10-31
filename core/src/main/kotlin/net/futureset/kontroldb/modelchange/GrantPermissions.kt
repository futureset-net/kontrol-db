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
    data class GrantPermissionBuilder(
        private val grantees: MutableSet<DbIdentifier> = mutableSetOf(),
        private val permissions: MutableSet<String> = mutableSetOf(),
        private var targetObject: SchemaObject? = null,
        private var targetObjectType: String = DbObjectType.TABLE.name,
    ) : Builder<GrantPermissionBuilder, GrantPermissions> {

        fun to(vararg grantees: String) = apply {
            this.grantees.addAll(grantees.map(::DbIdentifier))
        }

        fun permissions(vararg permissions: String) = apply {
            this.permissions.addAll(permissions)
        }

        fun on(name: String? = null, block: SchemaObjectBuilder.() -> Unit = {}) {
            this.targetObject = (this.targetObject?.let(::SchemaObjectBuilder) ?: SchemaObjectBuilder())
                .apply { name?.let(::name) }.apply(block).build()
        }

        fun objectType(objectType: String) = apply {
            this.targetObjectType = objectType
        }

        fun objectType(objectType: DbObjectType) = apply {
            objectType(objectType.identifier())
        }

        override fun build(): GrantPermissions {
            return GrantPermissions(
                grantees = grantees.apply { require(isNotEmpty()) },
                permissions = permissions.apply { require(isNotEmpty()) },
                targetObject = requireNotNull(targetObject),
                targetObjectType = targetObjectType,
            )
        }
    }
}

fun ModelChangesBuilder.grantPermissions(lambda: GrantPermissions.GrantPermissionBuilder.() -> Unit): GrantPermissions =
    GrantPermissions.GrantPermissionBuilder().apply(lambda).build().apply(changes::add)

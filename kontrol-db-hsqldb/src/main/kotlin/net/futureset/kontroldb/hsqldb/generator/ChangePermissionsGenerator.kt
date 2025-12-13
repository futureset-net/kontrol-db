package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.GrantOrRevoke.GRANT
import net.futureset.kontroldb.modelchange.GrantOrRevoke.REVOKE
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class ChangePermissionsGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<ChangePermissions>(es, ChangePermissions::class) {
    override fun convert(change: ChangePermissions): List<String> = change.grantees.flatMap { grantee ->
        change.permissions.map { perm ->
            "${change.grantOrRevoke} " +
                "${perm}${change.targetObject.toQuoted { " ON ${change.targetObjectType} $it" }}" +
                " ${if (change.grantOrRevoke == GRANT) "TO" else "FROM"}" +
                " ${grantee.toQuoted()}${" RESTRICT".takeIf { change.grantOrRevoke == REVOKE }.orEmpty()}"
        }
    }

    override fun canApplyTo(es: EffectiveSettings) = es.databaseName == "hsqldb"
}

package net.futureset.kontroldb.hsqldb.template

import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.GrantOrRevoke.GRANT
import net.futureset.kontroldb.modelchange.GrantOrRevoke.REVOKE
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ChangePermissionsTemplate(db: EffectiveSettings) :
    DbAwareTemplate<ChangePermissions>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<ChangePermissions> = ChangePermissions::class

    override fun convert(change: ChangePermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "${change.grantOrRevoke} " +
                    "${perm}${change.targetObject.toSql { " ON ${change.targetObjectType} $it" }}" +
                    " ${if (change.grantOrRevoke == GRANT) "TO" else "FROM"}" +
                    " ${grantee.toSql()}${" RESTRICT".takeIf { change.grantOrRevoke == REVOKE }.orEmpty() }"
            }
        }
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings) = effectiveSettings.databaseName == "hsqldb"
}

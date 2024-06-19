package net.futureset.kontroldb.oracle.template

import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.GrantOrRevoke
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ChangePermissionsTemplate(db: EffectiveSettings) :
    DbAwareTemplate<ChangePermissions>(db, TemplatePriority.DATABASE) {

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean = effectiveSettings.databaseName == "oracle"

    override fun type(): KClass<ChangePermissions> {
        return ChangePermissions::class
    }

    override fun convert(change: ChangePermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "${change.grantOrRevoke} " +
                    "${perm}${change.targetObject.toSql { " ON $it" }}" +
                    " ${if (change.grantOrRevoke == GrantOrRevoke.GRANT) "TO" else "FROM"} ${grantee.toSql()}"
            }
        }
    }
}

package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority.DATABASE
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class ChangePermissionsTemplate(db: EffectiveSettings) : DbAwareTemplate<ChangePermissions>(db, DATABASE) {
    override fun type(): KClass<ChangePermissions> {
        return ChangePermissions::class
    }

    override fun convert(change: ChangePermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "${change.grantOrRevoke} ${perm}${change.targetObject.toSql { " ON $it" }} TO ${grantee.toSql()}"
            }
        }
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

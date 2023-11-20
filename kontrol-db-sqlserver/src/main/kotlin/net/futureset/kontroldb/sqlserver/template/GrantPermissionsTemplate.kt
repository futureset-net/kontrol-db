package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.GrantPermissions
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class GrantPermissionsTemplate(db: EffectiveSettings) :
    DbAwareTemplate<GrantPermissions>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<GrantPermissions> {
        return GrantPermissions::class
    }

    override fun convert(change: GrantPermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "GRANT ${perm}${change.targetObject.toSql { " ON $it" }} TO ${grantee.toSql()}"
            }
        }
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

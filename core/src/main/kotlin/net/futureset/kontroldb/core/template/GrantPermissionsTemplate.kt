package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.GrantPermissions
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class GrantPermissionsTemplate(db: EffectiveSettings) :
    DbAwareTemplate<GrantPermissions>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<GrantPermissions> {
        return GrantPermissions::class
    }

    override fun convert(change: GrantPermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "GRANT ${perm}${change.targetObject.toSql { " ON ${change.targetObjectType} $it" }} TO ${grantee.toSql()}"
            }
        }
    }
}

package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority.DATABASE
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class ChangePermissionsGenerator(db: EffectiveSettings) : DbAwareGenerator<ChangePermissions>(db, DATABASE) {
    override fun type(): KClass<ChangePermissions> {
        return ChangePermissions::class
    }

    override fun convert(change: ChangePermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "${change.grantOrRevoke} ${perm}${change.targetObject.toQuoted { " ON $it" }} TO ${grantee.toQuoted()}"
            }
        }
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

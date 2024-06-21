package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.GrantOrRevoke
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class ChangePermissionsGenerator(es: EffectiveSettings) : DbAwareGenerator<ChangePermissions>(es) {

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "oracle"

    override fun type(): KClass<ChangePermissions> = ChangePermissions::class

    override fun convert(change: ChangePermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "${change.grantOrRevoke} " +
                    "${perm}${change.targetObject.toQuoted { " ON $it" }}" +
                    " ${if (change.grantOrRevoke == GrantOrRevoke.GRANT) "TO" else "FROM"} ${grantee.toQuoted()}"
            }
        }
    }
}

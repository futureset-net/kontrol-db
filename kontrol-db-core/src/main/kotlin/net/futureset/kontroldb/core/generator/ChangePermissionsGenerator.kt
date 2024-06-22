package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.GrantOrRevoke
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class ChangePermissionsGenerator(es: EffectiveSettings) : DbAwareGenerator<ChangePermissions>(es, ChangePermissions::class) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convert(change: ChangePermissions): List<String> {
        return change.grantees.flatMap { grantee ->
            change.permissions.map { perm ->
                "${change.grantOrRevoke} " +
                    "${perm}${change.targetObject.toQuoted { " ON ${change.targetObjectType} $it" }}" +
                    " ${if (change.grantOrRevoke == GrantOrRevoke.GRANT) "TO" else "FROM"} ${grantee.toQuoted()}"
            }
        }
    }
}

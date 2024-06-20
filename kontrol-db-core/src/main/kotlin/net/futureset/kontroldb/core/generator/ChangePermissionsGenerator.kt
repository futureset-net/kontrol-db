package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.GrantOrRevoke
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class ChangePermissionsGenerator(db: EffectiveSettings) :
    DbAwareGenerator<ChangePermissions>(db, GeneratorPriority.DEFAULT) {
    override fun type(): KClass<ChangePermissions> {
        return ChangePermissions::class
    }

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

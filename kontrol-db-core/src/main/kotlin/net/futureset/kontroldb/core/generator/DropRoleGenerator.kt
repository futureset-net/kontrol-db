package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropRole
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class DropRoleGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<DropRole>(es, DropRole::class) {
    override val priority = GeneratorPriority.DEFAULT

    override fun convertSingle(): DropRole.() -> String? = {
        roleName.toQuoted { "DROP ROLE $it" }
    }
}

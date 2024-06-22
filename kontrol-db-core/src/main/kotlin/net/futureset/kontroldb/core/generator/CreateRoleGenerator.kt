package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateRole
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class CreateRoleGenerator(es: EffectiveSettings) : DbAwareGenerator<CreateRole>(es, CreateRole::class) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): CreateRole.() -> String? = {
        roleName.toQuoted { "CREATE ROLE $it" }
    }
}

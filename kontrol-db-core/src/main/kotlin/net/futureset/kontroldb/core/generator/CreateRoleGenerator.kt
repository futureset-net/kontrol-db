package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateRole
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class CreateRoleGenerator(db: EffectiveSettings) : DbAwareGenerator<CreateRole>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun type(): KClass<CreateRole> {
        return CreateRole::class
    }

    override fun convertSingle(): CreateRole.() -> String? = {
        roleName.toQuoted { "CREATE ROLE $it" }
    }
}

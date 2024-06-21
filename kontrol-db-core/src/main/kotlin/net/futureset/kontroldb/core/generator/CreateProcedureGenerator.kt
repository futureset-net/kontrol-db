package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateProcedure
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class CreateProcedureGenerator(db: EffectiveSettings) :
    DbAwareGenerator<CreateProcedure>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun type() = CreateProcedure::class

    override fun convertSingle(): CreateProcedure.() -> String? = {
        (body ?: path?.text()).let {
            if (wholeDefinition) it else "CREATE $it"
        }
    }
}

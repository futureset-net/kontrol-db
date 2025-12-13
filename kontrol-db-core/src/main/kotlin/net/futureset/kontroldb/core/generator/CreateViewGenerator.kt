package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateView
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class CreateViewGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<CreateView>(es, CreateView::class) {
    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): CreateView.() -> String? = {
        (body ?: path?.text()).let { if (wholeDefinition) it else "CREATE $it" }
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.CreateView
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class CreateViewGenerator(db: EffectiveSettings) : DbAwareGenerator<CreateView>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun type() = CreateView::class

    override fun convertSingle(): CreateView.() -> String? = {
        (body ?: path?.text()).let { if (wholeDefinition) it else "CREATE $it" }
    }
}

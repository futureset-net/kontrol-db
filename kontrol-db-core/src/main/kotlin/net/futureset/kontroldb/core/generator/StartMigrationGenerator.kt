package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.modelchange.StartBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class StartMigrationGenerator(es: EffectiveSettings) : DbAwareGenerator<StartBanner>(es, StartBanner::class) {

    override val priority = GeneratorPriority.DEFAULT

    override fun convert(change: StartBanner): List<String> {
        val state = es.startState
        return ScriptComment(state.toString()).let { template(it)?.convert(it)?.filterNotNull().orEmpty() }
    }
}

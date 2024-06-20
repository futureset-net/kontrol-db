package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.modelchange.StartBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class StartMigrationGenerator(val db: EffectiveSettings) : DbAwareGenerator<StartBanner>(db, GeneratorPriority.DEFAULT) {
    override fun type(): KClass<StartBanner> = StartBanner::class

    override fun convert(change: StartBanner): List<String> {
        val state = db.startState
        return ScriptComment(state.toString()).let { template(it)?.convert(it)?.filterNotNull().orEmpty() }
    }
}

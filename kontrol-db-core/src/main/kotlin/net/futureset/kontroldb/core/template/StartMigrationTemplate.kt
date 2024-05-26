package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.modelchange.StartBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class StartMigrationTemplate(val db: EffectiveSettings) : DbAwareTemplate<StartBanner>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<StartBanner> = StartBanner::class

    override fun convert(change: StartBanner): List<String> {
        val state = db.startState
        return ScriptComment(state.toString()).let { template(it)?.convert(it)?.filterNotNull().orEmpty() }
    }
}

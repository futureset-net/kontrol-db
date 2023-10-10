package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.modelchange.StartBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class StartMigrationTemplate(val db: EffectiveSettings) : DbAwareTemplate<StartBanner>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<StartBanner> = StartBanner::class

    override fun convert(change: StartBanner): List<String> {
        val state = db.startState
        return Comment(state.toString()).let { template(it)?.convert(it)?.filterNotNull().orEmpty() }
    }
}

package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.modelchange.StartBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class StartMigrationTemplate(val db: EffectiveSettings) : DbAwareTemplate<StartBanner>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<StartBanner> = StartBanner::class

    override fun convert(change: StartBanner): List<String> {
        val state = db.startState
        return Comment(state.toString()).let { template(it)?.convert(it)?.filterNotNull().orEmpty() }
    }
}

package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.EndBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class EndMigrationTemplate(db: EffectiveSettings) : DbAwareTemplate<EndBanner>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<EndBanner> = EndBanner::class
}

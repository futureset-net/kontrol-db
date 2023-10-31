package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.EndBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class EndMigrationTemplate(db: EffectiveSettings) : DbAwareTemplate<EndBanner>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<EndBanner> = EndBanner::class
}

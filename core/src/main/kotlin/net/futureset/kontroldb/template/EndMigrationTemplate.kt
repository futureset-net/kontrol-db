package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.EndBanner
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class EndMigrationTemplate(db: EffectiveSettings) : DbAwareTemplate<EndBanner>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<EndBanner> = EndBanner::class
}

package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class DropIfExistsTemplate(db: EffectiveSettings) : DbAwareTemplate<DropIfExists>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropIfExists> = DropIfExists::class

    override fun convertToSingleStatement(change: DropIfExists): String? =
        """DROP ${change.objectType} ${change.objectName.toSql()} IF EXISTS"""
}

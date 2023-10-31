package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropIfExistsTemplate(db: EffectiveSettings) : DbAwareTemplate<DropIfExists>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropIfExists> = DropIfExists::class

    override fun convertToSingleStatement(change: DropIfExists): String =
        """DROP ${change.objectType} ${change.objectName.toSql()} IF EXISTS"""
}

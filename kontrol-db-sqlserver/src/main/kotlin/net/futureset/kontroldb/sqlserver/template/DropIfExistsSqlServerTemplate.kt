package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropIfExistsSqlServerTemplate(db: EffectiveSettings) : DbAwareTemplate<DropIfExists>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<DropIfExists> = DropIfExists::class

    override fun convertToSingleStatement(change: DropIfExists): String =
        """DROP ${change.objectType} IF EXISTS ${change.objectName.toSql()}"""

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "sqlserver"
    }
}

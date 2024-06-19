package net.futureset.kontroldb.sqlserver.template

import net.futureset.kontroldb.modelchange.DropColumns
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropColumnsTemplate(db: EffectiveSettings) : DbAwareTemplate<DropColumns>(
    db,
    TemplatePriority.DATABASE,
) {

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean =
        effectiveSettings.databaseName == "sqlserver"

    override fun type(): KClass<DropColumns> {
        return DropColumns::class
    }

    override fun convertSingle(): DropColumns.() -> String? = {
        table.toSql { tab -> "ALTER TABLE $tab DROP COLUMN ${columns.columnNames()}" }
    }
}

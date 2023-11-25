package net.futureset.kontroldb.core.template

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
    TemplatePriority.DEFAULT,
) {
    override fun type(): KClass<DropColumns> {
        return DropColumns::class
    }

    override fun convert(change: DropColumns): List<String> {
        return change.columns
            .map { col -> change.table.toSql { "ALTER TABLE $it DROP COLUMN ${col.toSql()}" } }
    }
}

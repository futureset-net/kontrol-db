package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.AddColumns
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class AddColumnsTemplate(db: EffectiveSettings) : DbAwareTemplate<AddColumns>(
    db,
    TemplatePriority.DEFAULT,
) {
    override fun type(): KClass<AddColumns> {
        return AddColumns::class
    }

    override fun convert(change: AddColumns): List<String> {
        return change.columnDefinitions
            .map { col -> change.table.toSql { "ALTER TABLE $it ADD ${col.toSql()}" } }
    }
}

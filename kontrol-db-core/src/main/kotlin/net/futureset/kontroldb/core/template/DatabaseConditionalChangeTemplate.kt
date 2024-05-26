package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.DatabaseConditionalChange
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class DatabaseConditionalChangeTemplate(val db: EffectiveSettings) : DbAwareTemplate<DatabaseConditionalChange>(db, TemplatePriority.DEFAULT) {
    override fun type() = DatabaseConditionalChange::class

    override fun convert(change: DatabaseConditionalChange): List<String> {
        return if (change.dbPredicate(db.databaseName)) {
            otherTemplateOutput(change.wrappedChange)
        } else {
            emptyList()
        }
    }
}
